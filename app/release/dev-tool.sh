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

BLUE='\033[0;34m'
NC='\033[0m' # No Color

function ph1() {
  echo -e "${BLUE}"
  echo -e "$@"
  echo -e "-------------------------------------------${NC}"
  echo -e "${NC}"
}

function clean() {
  CLEAN_OPT=$(has_opt "-clean" $@ )
  if [ "$CLEAN_OPT" = "true" ] ; then
    ph1 "FRAMEWORK CLEAN RESOURCES..............."
    gradle clean
  fi
}

COMMAND=$1
shift

[ -e build/output.txt ] && rm build/output.txt

if [ "$COMMAND" = "run" ] ; then
  clean $@
  gradle release  && \
    ./build/release/app/server/bin/server.sh start --profile=console,h2,data,debug \
    $REPORT_TEMPLATE_DIR_CONFIG $@ | \
    tee build/output.txt
elif [ "$COMMAND" = "run-with-postgres" ] ; then
  clean $@
  gradle release  && \
    ./build/release/app/server/bin/server.sh start --profile=console,postgres,data,debug \
    $REPORT_TEMPLATE_DIR_CONFIG $@ | \
    tee build/output.txt
elif [ "$COMMAND" = "run-with" ] ; then
  clean $@
  gradle release  && \
    ./build/release/app/server/bin/server.sh start \
    $REPORT_TEMPLATE_DIR_CONFIG $@ | \
    tee build/output.txt
elif [ "$COMMAND" = "flyway" ] ; then
  clean $@
  gradle release  && \
    ./build/release/app/server/bin/flyway.sh run --profile=console,postgres,debug,flyway
elif [ "$COMMAND" = "stop" ] ; then
  ./build/release/app/server/bin/server.sh stop
elif [ "$COMMAND" = "deploy" ] ; then
  sudo rm -rf /mnt/data/jvm/demo/server
  sudo cp -r build/release/app/server /mnt/data/jvm/demo/server

else
  echo 'Usage: '
  echo '  run                    build and start server'
  echo '  run-with-postgres      build and start server'
  echo '  stop                   stop server daemon'
  echo 'Options: '
  echo '  --profile=legacy,debug,postgres         Run with profile legacy'
  echo '  --app.init-db=true                      Create sample data or not'
  echo '  --app.import-legacy=[test,recent,all]   Import legacy data'
fi
