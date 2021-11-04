  #!/usr/bin/env bash

window=false
if [ "$OSTYPE" = "msys" ] ; then
  window=true;
elif [[ "$OSTYPE" == "cygwin" ]]; then
  window=true;
elif [[ "$OSTYPE" == "win32" ]]; then
  window=true;
elif [[ "$OSTYPE" == "darwin20.0" ]]; then
  window=true;
fi

PROJECT_DIR=`cd "$bin"; pwd`
ROOT_DIR="$PROJECT_DIR/../.."

if $window; then
  ROOT_DIR=`cygpath --absolute --windows "$ROOT_DIR"`
fi

function has_opt() {
  OPT_NAME=$1
  shift
  #Par the parameters
  for i in "$@"; do
    if [[ $i == $OPT_NAME ]] ; then
      echo "true"
      return
    fi
  done
  echo "false"
}

function get_opt() {
  OPT_NAME=$1
  DEFAULT_VALUE=$2
  shift

  #Par the parameters
  for i in "$@"; do
    index=$(($index+1))
    if [[ $i == $OPT_NAME* ]] ; then
      value="${i#*=}"
      echo "$value"
      return
    fi
  done
  echo $DEFAULT_VALUE
}

function postgresNewDb() {
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -U $PG_ADMIN_USER  -c "DROP DATABASE IF EXISTS $DB_NAME"
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -U $PG_ADMIN_USER  -c "CREATE DATABASE $DB_NAME"
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -U $PG_ADMIN_USER  -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $PG_USER"
}


PG_CMD=$1
shift


PG_HOST='localhost'
PG_ADMIN_USER='postgres'
PG_PASSWORD='admin'
PG_USER='devteam'
DB_NAME='viondb'


if [ "$PG_CMD" = "createUser" ] ; then
  PGPASSWORD=$PG_PASSWORD dropuser  --if-exists  -h $PG_HOST -U $PG_ADMIN_USER  $PG_USER
  PGPASSWORD=$PG_PASSWORD createuser -h $PG_HOST -U $PG_ADMIN_USER $PG_USER
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -U $PG_ADMIN_USER  -c "ALTER USER $PG_USER WITH ENCRYPTED PASSWORD '$PG_USER'"
elif [ "$PG_CMD" = "newDb" ] ; then
  postgresNewDb
elif [ "$PG_CMD" = "psql" ] ; then
  PGPASSWORD=$PG_USER psql -h $PG_HOST -U $PG_USER $DB_NAME
elif [ "$PG_CMD" = "dump" ] ; then
  echo "args = $@"
  backup_file=$(get_opt --file '' $@)
  if [ "$backup_file" = "" ]; then
    mkdir -p  dbbackup
    backup_file="dbbackup/$DB_NAME.tar"
  fi
  PGPASSWORD=$PG_PASSWORD pg_dump -v -U $PG_ADMIN_USER -F t $DB_NAME > $backup_file
elif [ "$PG_CMD" = "restore" ] ; then
  backup_file=$(get_opt --file '' $@)
  if [ "$backup_file" = "" ]; then
    backup_file="dbbackup/$DB_NAME.tar"
  fi
  postgresNewDb
  PGPASSWORD=$PG_PASSWORD pg_restore -h $PG_HOST -U $PG_ADMIN_USER -d $DB_NAME  "$backup_file"
elif [ "$PG_CMD" = "clean" ] ; then
  PGPASSWORD=$PG_PASSWORD psql -h $PG_HOST -U $PG_ADMIN_USER  -c "DROP DATABASE IF EXISTS $DB_NAME"
  PGPASSWORD=$PG_PASSWORD dropuser  --if-exists  -h $PG_HOST -U $PG_ADMIN_USER  $PG_USER
else
  echo 'Usage: '
  echo "  ./postgres.sh createUser  Create user $PG_USER and password "
  echo ""
  echo "  ./postgres.sh newDb       Drop, Create db $DB_NAME, grant $PG_USER user permissions "
  echo ""
  echo "  ./postgres.sh psql        Run psql client with $PG_USER and $DB_NAME"
  echo ""
  echo "  ./postgres.sh dump        Dump data from db $DB_NAME to  dbbackup/$DB_NAME.tar"
  echo "              [--file=path/name]     Optional file name instead default dbbackup/$DB_NAME.tar"
  echo ""
  echo "  ./postgres.sh restore     Restore data from dbbackup/$DB_NAME.tar to db $DB_NAME"
  echo "              [--file=path/name]     Optional file name instead default dbbackup/$DB_NAME.tar"
  echo ""
  echo "  ./postgres.sh clean       Clean $PG_USER user and $DB_NAME db"
fi
