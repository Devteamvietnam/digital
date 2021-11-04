package com.devteam.module.settings.location.data;

import com.devteam.module.common.ClientInfo;
import com.devteam.module.data.db.sample.EntityDB;
import com.devteam.module.settings.currency.data.CurrencyData;
import com.devteam.module.settings.location.service.LocationService;
import com.devteam.module.settings.location.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LocationData extends ModuleEntityAssert {

  @Autowired
  private LocationService locationService;

  public CountryGroup ASIA;
  public CountryGroup ASIA_SE;
  public CountryGroup EURO;
  public CountryGroup AMERICA;
  public CountryGroup NORTH_AMERICA;
  public CountryGroup SOUTH_AMERICA;
  public CountryGroup AFRICA;
  public CountryGroup AUSTRALIA;
  public CountryGroup ANTARCTICA;
  public CountryGroup[] ALL_COUNTRY_GROUPS;

  public Country VIETNAM;
  public Country CAM_PU_CHIA;
  public Country USA;
  public Country SINGAPORE;
  public Country UK;
  public Country FRANCE;
  public Country GERMANY;
  public Country CHINA;
  public Country INDONESIA;
  public Country JAPAN;
  public Country CANADA;
  public Country MEXICO;
  public Country[] ALL_COUNTRIES;

  public State QUANG_NINH;
  public State QUANG_NAM;
  public State BARIA_VUNGTAU;
  public State BINH_DINH;
  public State CA_MAU;
  public State CAN_THO;
  public State DAK_LAK;
  public State DANANG;
  public State DIEN_BIEN;
  public State AN_GIANG;
  public State BAC_GIANG;
  public State BAC_KAN;
  public State BAC_LIEU;
  public State BAC_NINH;
  public State BEN_TRE;
  public State BINH_PHUOC;
  public State BINH_DUONG;
  public State BINH_THUAN;
  public State CAO_BANG;
  public State DAK_NONG;
  public State DONG_NAI;
  public State DONG_THAP;
  public State HA_GIANG;
  public State HA_NAM;
  public State HA_TINH;
  public State HAI_DUONG;
  public State HAU_GIANG;
  public State HOA_BINH;
  public State HUNG_YEN;
  public State KHANH_HOA;
  public State KIEN_GIANG;
  public State KOM_TUM;
  public State LAI_CHAU;
  public State LAM_DONG;
  public State LANG_SON;
  public State LAO_CAI;
  public State LONG_AN;
  public State QUANG_TRI;
  public State NGHE_AN;
  public State NINH_BINH;
  public State NINH_THUAN;
  public State PHU_THO;
  public State QUANG_BINH;
  public State QUANG_NGAI;
  public State TRA_VINH;
  public State SOC_TRANG;
  public State SON_LA;
  public State TAY_NINH;
  public State THAI_BINH;
  public State NAM_DINH;
  public State THAI_NGUYEN;
  public State THANH_HOA;
  public State THUA_THIEN_HUE;
  public State TUYEN_QUANG;
  public State VINH_LONG;
  public State VINH_PHUC;
  public State TIEN_GIANG;
  public State YEN_BAI;
  public State PHU_YEN;
  public State GIA_LAI;
  public State LIAONING;
  public State[] ALL_STATES;

  public City HANOI;
  public City TP_HCM;
  public City HAI_PHONG;
  public City HA_LONG;
  public City NEWYORK;
  public City SINGAPORE_CITY;
  public City DA_NANG;
  public City CAN_THO_CITY;
  public City VUNG_TAU_CITY;
  public City BA_RIA_CITY;
  public City QUY_NHON_CITY;
  public City CA_MAU_CITY;
  public City BUON_ME_THUAT_CITY;
  public City DIEN_BIEN_PHU_CITY;
  public City PLEIKU_CITY;
  public City CAM_RANH_CITY;
  public City RACH_GIA_CITY;
  public City DA_LAT_CITY;
  public City VINH_CITY;
  public City TUY_HOA_CITY;
  public City DONG_HOI_CITY;
  public City TAM_KY_CITY;
  public City HUE_CITY;
  public City BINH_DUONG_CITY;
  public City LONDON;
  public City LIVERPOOL;
  public City PARIS;
  public City BORDEAUX;
  public City SHENYANG;
  public City TOKYO;
  public City[] ALL_CITIES;

  public LocationType AIR_SERVICE;
  public LocationType SEA_SERVICE;
  public LocationType DEPOT_SERVICE;
  public LocationType INLAND_SERVICE;
  public LocationType TRUCK_SERVICE;
  public LocationType[] ALL_LOCATIONTYPE;


  public Location PORT_CAT_LAI_VNCLI;
  public Location PORT_HAI_PHONG_VNHPH;
  public Location PORT_DA_NANG_VNDAN;
  public Location PORT_VERDON_FRLVE;
  public Location PORT_LONDON_GBLON;
  public Location PORT_SINGAPORE_SGSIN;
  public Location PORT_TOKYO;
  public Location AIRPORT_TAN_SON_NHAT_SGN;
  public Location AIRPORT_NOI_BAI_HAN;
  public Location AIRPORT_CAM_RANH_CXR;
  public Location AIRPORT_RACH_GIA_PQC;
  public Location AIRPORT_PHU_QUOC_PQC;
  public Location AIRPORT_LIEN_KHUONG_DLI;
  public Location AIRPORT_VINH_VII;
  public Location AIRPORT_TUY_HOA_TBB;
  public Location AIRPORT_DONG_HOI_VDH;
  public Location AIRPORT_CHU_LAI_VCL;
  public Location AIRPORT_PHU_BAI_HUI;
  public Location AIRPORT_DA_NANG_DAD;
  public Location AIRPORT_SINGAPORE_CHANGI_SIN;
  public Location AIRPORT_LONDON_HEATHROWH_LHR;
  public Location SONG_THAN_INDUSTRIAL_ZONE;
  public Location PORTA_FOOD;
  public Location[] ALL_LOCATIONS;

  public CountryCountryGroupRelation   ASIA_VIETNAM;
  public CountryCountryGroupRelation   ASIA_SE_VIETNAM;
  public CountryCountryGroupRelation   ASIA_CAMPUCHIA;
  public CountryCountryGroupRelation   ASIA_SE_CAMPUCHIA;
  public CountryCountryGroupRelation   ASIA_INDONESIA;
  public CountryCountryGroupRelation   ASIA_SE_INDONESIA;
  public CountryCountryGroupRelation   ASIA_CHINA;
  public CountryCountryGroupRelation   ASIA_SINGAPORE;
  public CountryCountryGroupRelation   ASIA_SE_SINGAPORE;
  public CountryCountryGroupRelation   AMERICA_USA;
  public CountryCountryGroupRelation   EURO_UK;
  public CountryCountryGroupRelation   EURO_FR;
  public CountryCountryGroupRelation   EURO_GERMANY;
  public CountryCountryGroupRelation[] ALL;

  public void initialize(ClientInfo client) {
    initCountryGroup(client);
    initCountry(client);
    initState(client);
    initCity(client);
    initLocationType(client);
    initLocation(client);
    initCountryCountryGroupRelation(client);
  }


  void initCountryGroup(ClientInfo client) {

    ASIA     = new CountryGroup("ASIA","AS");
    ASIA_SE  = new CountryGroup("SOUTHEAST ASIA", "SEA").withParent(ASIA);
    EURO    = new CountryGroup("EUROPE","EU");
    AMERICA   = new CountryGroup("AMERICA","AM" );
    NORTH_AMERICA   = new CountryGroup("NORTH AMERICA","NAM" ).withParent(AMERICA);
    SOUTH_AMERICA   = new CountryGroup("SOUTH AMERICA","SAM" ).withParent(AMERICA);
    AFRICA    = new CountryGroup("AFRICA", "AF");
    AUSTRALIA = new CountryGroup("AUSTRALIA", "AUS" );
    ANTARCTICA = new CountryGroup("ANTARCTICA","AQ");

    ALL_COUNTRY_GROUPS = new CountryGroup[] {
        ASIA, ASIA_SE, EURO, AMERICA, NORTH_AMERICA, SOUTH_AMERICA, AFRICA, AUSTRALIA, ANTARCTICA
    };
    for(CountryGroup group : ALL_COUNTRY_GROUPS) {
      CountryGroup parent = group.getParent();
      if (parent != null) {
        parent = locationService.getCountryGroup(client, parent.getName());
      }
      locationService.createCountryGroup(client, parent, group);
    }
  }

  void initCountry(ClientInfo client) {
    CurrencyData CURRENCY_DATA = EntityDB.getInstance().getData(CurrencyData.class);
    String ADDRESS_FORMAT = "Street, Ward, District, City";

    VIETNAM = new Country("VN", "VIETNAM",ADDRESS_FORMAT, "084").withCurrency(CURRENCY_DATA.VND);

    CAM_PU_CHIA = new Country("CPC", "CAM_PU_CHIA",ADDRESS_FORMAT, "855").withCurrency(CURRENCY_DATA.RIEL);

    INDONESIA = new Country("ID", "INDONESIA",ADDRESS_FORMAT, "062").withCurrency(CURRENCY_DATA.RUPIAH);

    CHINA = new Country("CN", "CHINA",ADDRESS_FORMAT, "086").withCurrency(CURRENCY_DATA.CNY);

    SINGAPORE = new Country("SG", "SINGAPORE",ADDRESS_FORMAT, "065").withCurrency(CURRENCY_DATA.SGD);

    USA = new Country("US", "UNITED STATES", ADDRESS_FORMAT, "001").withCurrency(CURRENCY_DATA.USD);

    UK = new Country("UK", "UNITED KINGDOM", ADDRESS_FORMAT, "044").withCurrency(CURRENCY_DATA.GBP);

    FRANCE = new Country("FR", "FRANCE", ADDRESS_FORMAT, "033").withCurrency(CURRENCY_DATA.EUR);

    GERMANY = new Country("DE", "GERMANY", ADDRESS_FORMAT, "049").withCurrency(CURRENCY_DATA.EUR);

    JAPAN = new Country("JP", "JAPAN", ADDRESS_FORMAT, "081").withCurrency(CURRENCY_DATA.JPY);

    CANADA = new Country("CA", "CANADA", ADDRESS_FORMAT, "001").withCurrency(CURRENCY_DATA.CAD);

    MEXICO = new Country("MX", "MEXICO", ADDRESS_FORMAT, "052").withCurrency(CURRENCY_DATA.USD);

    ALL_COUNTRIES = new Country[] {
        VIETNAM, USA, SINGAPORE, UK, FRANCE, GERMANY, CHINA, CAM_PU_CHIA, INDONESIA, JAPAN, CANADA, MEXICO
    };
    for (Country country : ALL_COUNTRIES) {
      locationService.saveCountry(client, country);
    }
  }

  void initState(ClientInfo client) {

    QUANG_NINH = new State("001", "Quang Ninh").withCountry(VIETNAM);

    QUANG_NAM = new State("VN QNM", "Quang Nam").withCountry(VIETNAM);

    BARIA_VUNGTAU = new State("003", "Ba Ria_Vung Tau").withCountry(VIETNAM);

    BINH_DINH = new State("004", "Binh Dinh").withCountry(VIETNAM);

    CA_MAU = new State("005", "Ca Mau").withCountry(VIETNAM);

    CAN_THO = new State("006", "Can Tho").withCountry(VIETNAM);

    DAK_LAK = new State("007", "Dlak Lak").withCountry(VIETNAM);

    DANANG = new State("008", "Da Nang").withCountry(VIETNAM);

    DIEN_BIEN = new State("009", "Dien Bien").withCountry(VIETNAM);

    GIA_LAI = new State("010", "Gia Lai").withCountry(VIETNAM);

    AN_GIANG  = new State("011", "An Giang").withCountry(VIETNAM);

    BAC_GIANG  = new State("012", "Bac Giang").withCountry(VIETNAM);

    BAC_KAN  = new State("013", "Bac Kan").withCountry(VIETNAM);

    BAC_LIEU  = new State("014", "Bac Lieu").withCountry(VIETNAM);

    BAC_NINH  = new State("015", "Bac Ninh").withCountry(VIETNAM);

    BEN_TRE  = new State("016", "Ben Tre").withCountry(VIETNAM);

    BINH_DUONG  = new State("017", "Binh Duong").withCountry(VIETNAM);

    BINH_PHUOC  = new State("018", "Binh Phuoc").withCountry(VIETNAM);

    BINH_THUAN  = new State("019", "Binh Thuan").withCountry(VIETNAM);

    CAO_BANG  = new State("020", "Cao Bang").withCountry(VIETNAM);

    DAK_NONG  = new State("021", "Dak Nong").withCountry(VIETNAM);

    DONG_NAI  = new State("022", "Dong Nai").withCountry(VIETNAM);

    DONG_THAP  = new State("023", "Dong Thap").withCountry(VIETNAM);

    HA_GIANG  = new State("024", "Ha Giang").withCountry(VIETNAM);

    HA_NAM  = new State("025", "Ha Nam").withCountry(VIETNAM);

    HA_TINH  = new State("026", "Ha Tinh").withCountry(VIETNAM);

    HAI_DUONG  = new State("027", "Hai Duong").withCountry(VIETNAM);

    HAU_GIANG  = new State("028", "Hau Giang").withCountry(VIETNAM);

    HOA_BINH  = new State("029", "Hoa Binh").withCountry(VIETNAM);

    HUNG_YEN  = new State("030", "Hung Yen").withCountry(VIETNAM);

    KHANH_HOA  = new State("031", "Khanh Hoa").withCountry(VIETNAM);

    KIEN_GIANG  = new State("032", "Kien Giang").withCountry(VIETNAM);

    KOM_TUM  = new State("033", "Kom Tum").withCountry(VIETNAM);

    LAI_CHAU  = new State("034", "Lai Chau").withCountry(VIETNAM);

    LAM_DONG  = new State("035", "Lam Dong").withCountry(VIETNAM);

    LANG_SON  = new State("036", "Lang Son").withCountry(VIETNAM);

    LAO_CAI  = new State("037", "Lao Cai").withCountry(VIETNAM);

    LONG_AN  = new State("038", "Long An").withCountry(VIETNAM);

    NAM_DINH  = new State("040", "Nam Dinh").withCountry(VIETNAM);

    NGHE_AN  = new State("041", "Nghệ An").withCountry(VIETNAM);

    NINH_BINH  = new State("042", "Ninh Binh").withCountry(VIETNAM);

    NINH_THUAN  = new State("043", "Ninh Thuan").withCountry(VIETNAM);

    PHU_THO  = new State("044", "Phu Tho").withCountry(VIETNAM);

    QUANG_BINH  = new State("045", "Quang Binh").withCountry(VIETNAM);

    QUANG_NGAI  = new State("046", "Quang Ngai").withCountry(VIETNAM);

    QUANG_TRI  = new State("047", "Quang Tri").withCountry(VIETNAM);

    SOC_TRANG  = new State("048", "Soc Trang").withCountry(VIETNAM);

    SON_LA  = new State("049", "Son La").withCountry(VIETNAM);

    TAY_NINH  = new State("050", "Tay Ninh").withCountry(VIETNAM);

    THAI_BINH  = new State("051", "Thai Binh").withCountry(VIETNAM);

    THAI_NGUYEN  = new State("052", "Thai Nguyen").withCountry(VIETNAM);

    THANH_HOA  = new State("053", "Thanh Hoa").withCountry(VIETNAM);

    THUA_THIEN_HUE  = new State("054", "Thua Thien Hue").withCountry(VIETNAM);

    TIEN_GIANG  = new State("055", "Tien Giang").withCountry(VIETNAM);

    TRA_VINH  = new State("056", "Tra Vinh").withCountry(VIETNAM);

    TUYEN_QUANG  = new State("057", "Tuyen Quang").withCountry(VIETNAM);

    VINH_LONG  = new State("058", "Vinh Long").withCountry(VIETNAM);

    VINH_PHUC  = new State("059", "Vinh Phuc").withCountry(VIETNAM);

    YEN_BAI  = new State("060", "Yen Bai").withCountry(VIETNAM);

    PHU_YEN  = new State("061", "Phu Yen").withCountry(VIETNAM);

    LIAONING  = new State("062", "Liaoning").withCountry(CHINA);

    ALL_STATES = new State[]  { QUANG_NINH, QUANG_NAM, BARIA_VUNGTAU, BINH_DINH, CA_MAU, CAN_THO,
        DAK_LAK, DANANG, DIEN_BIEN, AN_GIANG, BAC_GIANG, BAC_KAN, BAC_LIEU, BAC_NINH, BEN_TRE, BINH_DUONG, BINH_PHUOC,
        BINH_THUAN, CAO_BANG, DAK_NONG, DONG_NAI, DONG_THAP, HA_GIANG, HA_NAM, HA_TINH, HAI_DUONG, HAU_GIANG, HOA_BINH,
        HUNG_YEN, KHANH_HOA, KIEN_GIANG, KOM_TUM, LAI_CHAU, LAM_DONG, LANG_SON, LAO_CAI, LONG_AN, NAM_DINH, NGHE_AN,
        NINH_BINH, NINH_THUAN, PHU_THO, QUANG_BINH, QUANG_NGAI, QUANG_TRI, SOC_TRANG, SON_LA, TAY_NINH, THAI_BINH,
        THAI_NGUYEN, THANH_HOA, THUA_THIEN_HUE, TIEN_GIANG, TRA_VINH, TUYEN_QUANG, VINH_LONG, VINH_PHUC, YEN_BAI, PHU_YEN,
        GIA_LAI, LIAONING
    };

    for (State state : ALL_STATES) {
      locationService.createState(client, state);
    }
  }

  void initCity(ClientInfo client) {

    HANOI = new City("VNHAN", "Ha Noi City").withCountry(VIETNAM);
    TP_HCM = new City("VNSGN", "Ho Chi Minh City").withCountry(VIETNAM);
    HA_LONG = new City("VNHLG", "Ha Long").withCountry(VIETNAM);
    HAI_PHONG = new City("VNHPH", "Hai Phong").withCountry(VIETNAM);
    DA_NANG = new City("VNDAD", "Da Nang").withCountry(VIETNAM);
    CAN_THO_CITY = new City("VNVCA", "Can Tho").withCountry(VIETNAM);
    VUNG_TAU_CITY = new City("VNVUT", "Vung Tau").withCountry(VIETNAM);
    BA_RIA_CITY = new City("VN7BR", "Ba Ria").withCountry(VIETNAM);
    QUY_NHON_CITY = new City("VNUIH", "Quy Nhon").withCountry(VIETNAM);
    CA_MAU_CITY = new City("VNCAH", "Ca Mau").withCountry(VIETNAM);
    BUON_ME_THUAT_CITY = new City("VNTHQ", "Buon me thuat").withCountry(VIETNAM);
    DIEN_BIEN_PHU_CITY = new City("VNDBP", "Dien Bien Phu").withCountry(VIETNAM);
    PLEIKU_CITY = new City("VNKUG", "Pleiku").withCountry(VIETNAM);
    CAM_RANH_CITY = new City("VNCXR", "Cam Ranh").withCountry(VIETNAM);
    RACH_GIA_CITY = new City("VNVKG", "Rach Gia").withCountry(VIETNAM);
    DA_LAT_CITY = new City("VNDLI", "Da Lat").withCountry(VIETNAM);
    VINH_CITY = new City("VNVNH", "Vinh").withCountry(VIETNAM);
    TUY_HOA_CITY = new City("VNTBB", "Tuy Hoa").withCountry(VIETNAM);
    DONG_HOI_CITY = new City("VNDOH", "Dong Hoi").withCountry(VIETNAM);
    TAM_KY_CITY = new City("VNTMK", "Tam Ky").withCountry(VIETNAM);
    HUE_CITY = new City("VNHUI", "Hue").withCountry(VIETNAM);
    BINH_DUONG_CITY = new City("VNBDU", "Binh Duong").withCountry(VIETNAM);

    NEWYORK = new City("212", "New York City").withCountry(USA);
    SINGAPORE_CITY = new City("065", "Singapore City").withCountry(SINGAPORE);
    LONDON = new City("213", "London City").withCountry(UK);
    LIVERPOOL = new City("214", "Liverpool City").withCountry(UK);
    PARIS = new City("215", "Capital City Of France").withCountry(FRANCE);
    BORDEAUX = new City("216", "Bordeaux City").withCountry(FRANCE);
    SHENYANG = new City("217", "SHENYANG City").withCountry(CHINA);
    TOKYO = new City("218", "Tokyo City").withCountry(JAPAN);

    ALL_CITIES = new City[] { HANOI, TP_HCM, HAI_PHONG, HA_LONG, NEWYORK, SINGAPORE_CITY, DA_NANG,
        CAN_THO_CITY, VUNG_TAU_CITY, BA_RIA_CITY, QUY_NHON_CITY, CA_MAU_CITY, BUON_ME_THUAT_CITY, DIEN_BIEN_PHU_CITY,
        PLEIKU_CITY,CAM_RANH_CITY, RACH_GIA_CITY, DA_LAT_CITY, VINH_CITY, TUY_HOA_CITY, DONG_HOI_CITY, TAM_KY_CITY,
        HUE_CITY, BINH_DUONG_CITY, LONDON, LIVERPOOL, PARIS, BORDEAUX, SHENYANG, TOKYO
    };
    for(City city : ALL_CITIES) {
      if(city.getStateCode() != null) {
        State state = locationService.getState(client, city.getStateCode());
        locationService.createCity(client, state, city);
      } else {
        Country countryInDB = locationService.getCountry(client, city.getCountryCode());
        city.withCountry(countryInDB);
        locationService.createCity(client, countryInDB, city);
      }
    }
  }

  void initLocationType(ClientInfo client) {
    AIR_SERVICE = new LocationType("Air Service", "AIR");
    SEA_SERVICE = new LocationType("Sea Service", "SEA");
    DEPOT_SERVICE = new LocationType("Depot Service", "DEPOT");
    INLAND_SERVICE = new LocationType("Inland Service", "INLAND");
    TRUCK_SERVICE = new LocationType("Truck Service", "TRUCK");

    ALL_LOCATIONTYPE = new LocationType[]{ AIR_SERVICE, SEA_SERVICE, DEPOT_SERVICE, INLAND_SERVICE, TRUCK_SERVICE };
    for(LocationType locationType : ALL_LOCATIONTYPE) {
      locationService.saveLocationType(client, locationType);
    }
  }

  void initLocation(ClientInfo client) {
    // Sea port
    PORT_CAT_LAI_VNCLI =
        new Location("VNCLI", "CAT LAI PORT JSC").withCountry(VIETNAM).withCity(TP_HCM)
        .withLocationType(List.of(SEA_SERVICE, TRUCK_SERVICE, AIR_SERVICE));

    PORT_HAI_PHONG_VNHPH =
        new Location("VNHPH", "PORT OF HAI PHONG").withCountry(VIETNAM).withCity(HAI_PHONG)
        .withLocationType(List.of(SEA_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VNHPH", "UNLOCODE", "Hai Phong UNLOCODE"))
        .withAliases(new LocationAlias("HPH", "UNLOCODE_3L", "Hai Phong UNLOCODE_3L"));

    PORT_DA_NANG_VNDAN = new Location("VNDAD", "DA NANG PORT").withCountry(VIETNAM).withCity(DA_NANG)
        .withLocationType(List.of(SEA_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VNDAD", "UNLOCODE", "Da Nang Port UNLOCODE"))
        .withAliases(new LocationAlias("55204", "US Code", "Da Nang Port US Code"));

    PORT_SINGAPORE_SGSIN =
        new Location("SGSIN", "Port Of Singapore").withCountry(SINGAPORE).withCity(SINGAPORE_CITY)
        .withLocationType(List.of(TRUCK_SERVICE))
        .withAliases(new LocationAlias("SGSIN", "UNLOCODE", "Singapore UNLOCODE"))
        .withAliases(new LocationAlias("55976", "US Code", "Singapore US Code"));

    PORT_VERDON_FRLVE =
        new Location("FRLVE", "LE VERDON-SUR-MER, GIRONDE").withCountry(FRANCE).withCity(BORDEAUX)
        .withLocationType(List.of(TRUCK_SERVICE))
        .withAliases(new LocationAlias("FRLVE", "UNLOCODE", "Le Verdon Sur Mer UNLOCODE"))
        .withAliases(new LocationAlias("42742", "US Code", "Le Verdon Sur Mer US Code"))
        .withAliases(new LocationAlias("LVE", "UNLOCODE_3L", "Le Verdon Sur Mer UNLOCODE"));

    PORT_LONDON_GBLON =
        new Location("GBLON", "PORT OF LONDON, ENGLAND").withCountry(UK).withCity(LONDON)
        .withLocationType(List.of(TRUCK_SERVICE))
        .withAliases(new LocationAlias("GBLON", "UNLOCODE", "LON DON UNLOCODE"));

    PORT_TOKYO =
        new Location("JPSEA", "Setagaya-ku/Tokyo, Japan").withCountry(JAPAN).withCity(TOKYO)
        .withLocationType(List.of(TRUCK_SERVICE))
        .withAliases(new LocationAlias("SEA", "UNLONCODE_3L", "Japan UNILOCODE_3L"))
        .withAliases(new LocationAlias("JPSEA", "UNLOCODE", "Japan UNLOCODE"));

    // Air port
    AIRPORT_LONDON_HEATHROWH_LHR =
        new Location("LHR", "LONDON HEATHROWH INTERNATIONAL AIRPORT, ENGLAND").withCountry(UK).withCity(LONDON)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("LHR", "IATA_Code", "London Heathrowh IATA Code"));

    AIRPORT_TAN_SON_NHAT_SGN =
        new Location("SGN", "TAN SON NHAT INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(TP_HCM)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("SGN", "IATA CODE", "Ho Chi Minh IATA Code"));

    AIRPORT_DA_NANG_DAD =
        new Location("DAD", "DA NANG INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(DA_NANG)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("DAD", "UNLOCODE_3L", "Da nang UNLOCODE_3L"));

    AIRPORT_NOI_BAI_HAN =
        new Location("HAN", "NOI BAI INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(HANOI)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("HAN", "UNLOCODE", "Ha Noi UNLOCODE"));

    AIRPORT_CAM_RANH_CXR =
        new Location("CXR", "CAM RANH INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(CAM_RANH_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("CXR", "UNLOCODE", "Cam Ranh UNLOCODE"));

    AIRPORT_RACH_GIA_PQC =
        new Location("VKG", "RACH GIA AIRPORT").withCountry(VIETNAM).withCity(RACH_GIA_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VKG", "UNLOCODE", "Rach Gia UNLOCODE"));

    AIRPORT_PHU_QUOC_PQC =
        new Location("PQC", "PHU QUOC INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(RACH_GIA_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("PQC", "UNLOCODE_3L", "Phu Quoc UNLOCODE(3L)"));

    AIRPORT_LIEN_KHUONG_DLI =
        new Location("DLI", "LIEN KHUONG AIRPORT").withCountry(VIETNAM).withCity(DA_LAT_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("DLI", "UNLOCODE_3L", "Lien Khuong UNLOCODE(3L)"));

    AIRPORT_VINH_VII =
        new Location("VII", "VINH INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(VINH_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VII", "UNLOCODE_3L", "Vinh UNLOCODE(3L)"));

    AIRPORT_TUY_HOA_TBB =
        new Location("TBB", "TUY HOA AIRPORT").withCountry(VIETNAM).withCity(TUY_HOA_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("TBB", "UNLOCODE_3L", "Tuy Hoa UNLOCODE(3L)"));

    AIRPORT_DONG_HOI_VDH =
        new Location("VDH", "DONG HOI AIRPORT").withCountry(VIETNAM).withCity(DONG_HOI_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VDH", "UNLOCODE_3L", "Dong Hoi UNLOCODE(3L)"));

    AIRPORT_CHU_LAI_VCL =
        new Location("VCL", "CHU LAI AIRPORT").withCountry(VIETNAM).withCity(TAM_KY_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("VCL", "UNLOCODE_3L", "Chu Lai UNLOCODE(3L)"));

    AIRPORT_PHU_BAI_HUI =
        new Location("HUI", "PHU BAI INTERNATIONAL AIRPORT").withCountry(VIETNAM).withCity(HUE_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("HUI", "IATACODE", "Phu Bai IATACODE"));

    AIRPORT_SINGAPORE_CHANGI_SIN =
        new Location("SIN", "SINGAPORE CHANGI INTERNATIONAL AIRPORT").withCountry(SINGAPORE).withCity(SINGAPORE_CITY)
        .withLocationType(List.of(AIR_SERVICE, SEA_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("SIN", "IATACODE", "Singapore IATACODE"));

    SONG_THAN_INDUSTRIAL_ZONE =
        new Location("BDT", "SONG THAN INDUSTRIAL ZONE").withCountry(VIETNAM).withCity(BINH_DUONG_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("BDT", "UNLOCODE_3L", "Song Than UNLOCODE_3L"));

    PORTA_FOOD =
        new Location("POR", "PORTA Fine Food & Import Com").withCountry(SINGAPORE).withCity(SINGAPORE_CITY)
        .withLocationType(List.of(AIR_SERVICE, TRUCK_SERVICE))
        .withAliases(new LocationAlias("POR", "UNLOCODE_3L", "Singapore UNLOCODE_3L"));

    ALL_LOCATIONS = new Location[] {
        PORT_CAT_LAI_VNCLI, PORT_HAI_PHONG_VNHPH, PORT_DA_NANG_VNDAN, PORT_VERDON_FRLVE,

        //Foreign Port
        PORT_LONDON_GBLON,  PORT_SINGAPORE_SGSIN, PORT_TOKYO,

        AIRPORT_TAN_SON_NHAT_SGN, AIRPORT_NOI_BAI_HAN,  AIRPORT_CAM_RANH_CXR,
        AIRPORT_RACH_GIA_PQC, AIRPORT_PHU_QUOC_PQC, AIRPORT_LIEN_KHUONG_DLI, AIRPORT_VINH_VII, AIRPORT_TUY_HOA_TBB,
        AIRPORT_DONG_HOI_VDH, AIRPORT_CHU_LAI_VCL, AIRPORT_PHU_BAI_HUI, AIRPORT_DA_NANG_DAD,

        //Foreign Airport
        AIRPORT_SINGAPORE_CHANGI_SIN, AIRPORT_LONDON_HEATHROWH_LHR,

        SONG_THAN_INDUSTRIAL_ZONE, PORTA_FOOD,
    };
    for (Location location : ALL_LOCATIONS) {
      if (location.getCityCode() != null) {
        City city = locationService.loadCity(client, location.getCityCode());
        locationService.createLocation(client, city, location);
      } else {
        State state = locationService.getState(client, location.getStateCode());
        locationService.createCity(client, state, new City(location.getCityCode()));
      }
    }
  }

  void initCountryCountryGroupRelation(ClientInfo client) {

    ASIA_VIETNAM = new CountryCountryGroupRelation(ASIA, VIETNAM);
    ASIA_SE_VIETNAM = new CountryCountryGroupRelation(ASIA_SE, VIETNAM);
    ASIA_CAMPUCHIA = new CountryCountryGroupRelation(ASIA, CAM_PU_CHIA);
    ASIA_SE_CAMPUCHIA = new CountryCountryGroupRelation(ASIA_SE, CAM_PU_CHIA);
    ASIA_INDONESIA = new CountryCountryGroupRelation(ASIA,INDONESIA);
    ASIA_SE_INDONESIA = new CountryCountryGroupRelation(ASIA_SE, INDONESIA);
    ASIA_CHINA = new CountryCountryGroupRelation(ASIA, CHINA);
    ASIA_SINGAPORE = new CountryCountryGroupRelation(ASIA, SINGAPORE);
    ASIA_SE_SINGAPORE = new CountryCountryGroupRelation(ASIA_SE, SINGAPORE);
    AMERICA_USA= new CountryCountryGroupRelation(AMERICA, USA);
    EURO_UK = new CountryCountryGroupRelation(EURO, UK);
    EURO_FR = new CountryCountryGroupRelation(EURO, FRANCE);
    EURO_GERMANY = new CountryCountryGroupRelation(EURO, GERMANY);

    ALL = new CountryCountryGroupRelation[] {
        ASIA_VIETNAM, ASIA_SE_VIETNAM, ASIA_CAMPUCHIA, ASIA_SE_CAMPUCHIA, ASIA_INDONESIA, ASIA_SE_INDONESIA, ASIA_CHINA,
        ASIA_SINGAPORE, ASIA_SE_SINGAPORE, AMERICA_USA, EURO_UK, EURO_FR, EURO_GERMANY, };

    for (CountryCountryGroupRelation relation : ALL) {
      locationService.createCountryGroupRelation(client, relation);
    }
  }

  public void assertAll(ClientInfo client) throws Exception {
    new CountryGroupAssert(client, ASIA_SE)
    .assertEntityCreated()
    .assertEntityUpdate();

    new CountryAssert(client, VIETNAM)
    .assertEntityCreated()
    .assertEntityUpdate()
    .assertEntitySearch()
    .assertEntityArchive();

    new StateAssert(client, THAI_NGUYEN)
    .assertEntityCreated()
    .assertEntityUpdate()
    .assertEntitySearch()
    .assertEntityArchive();

    new CityAssert(client, HAI_PHONG)
    .assertEntityCreated()
    .assertEntityUpdate()
    .assertEntitySearch()
    .assertEntityArchive();

    new LocationTypeAssert(client, SEA_SERVICE)
    .assertEntityCreated()
    .assertEntitySearch()
    .assertEntityUpdate()
    .assertEntityArchive();

    new LocationAssert(client, PORT_HAI_PHONG_VNHPH)
    .assertEntityCreated()
    .assertEntitySearch()
    .assertEntityUpdate()
    .assertEntityArchive();

    new CountryCountryGroupRelationAssert(client, ASIA, VIETNAM)
    .assertEntityCreated();
  }
}
