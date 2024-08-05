package cl.sii.crs2.sara.export.util;

import cl.sii.crs2.sara.export.domain.crs.v2.oecd.ties.isocrstypes.v1.CountryCodeType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


public enum Countries {
    INSTANCE;

    public Map<String, String> byName = new TreeMap<String, String>();
    public Map<String, String> byIso = new TreeMap<String, String>();

    private Countries() {
        Locale en = new Locale("en", "EN");
        Locale es = new Locale("es", "CL");
        for (CountryCodeType country : CountryCodeType.values()) {
            String iso = country.name();
            Locale loc = new Locale("en", iso);
            String[] countryNames = { loc.getDisplayCountry(en), loc.getDisplayCountry(es) };
            int index = 0;
            for (String countryName : countryNames) {
                if (index == 0) {
                    byIso.put(iso, countryName);
                }
                byName.put(countryName, iso);
                byName.put(Strings.simplify(countryName), iso);
                if (countryName.contains("Hong Kong")) {
                    byName.put("Hong Kong, China", iso);
                } else if (countryName.equals("Isle Of Man")) {
                    byName.put("Isle of Man", iso);
                } else if (countryName.equals("South Korea")) {
                    byName.put("Korea", iso);
                } else if (countryName.equals("Slovakia")) {
                    byName.put("Slovak Republic", iso);
                }
                index++;
            }
            byName.put("Islas Caymán", "KY");
            byName.put(Strings.simplify("Islas Caymán"), "KY");
            byName.put("Inglaterra", "GB");
            byName.put(Strings.simplify("Inglaterra"), "GB");
            byName.put("Macau, China", "MO");
            byName.put(Strings.simplify("Macau, China"), "MO");
            byName.put("Saint Kitts and Nevis", "KN");
            byName.put(Strings.simplify("Saint Kitts and Nevis"), "KN");
            byName.put("Saint Vincent and the Grenadines", "VC");
            byName.put(Strings.simplify("Saint Vincent and the Grenadines"), "VC");
            byName.put("Turks and Caicos Islands", "TC");
            byName.put(Strings.simplify("Turks and Caicos Islands"), "TC");
            {
                // Jersey (JE) - Largest
                // Guernsey (GE)
                byName.put("Channel Island", "JE");
                byName.put(Strings.simplify("Channel Island"), "JE");
            }
            //
            byName.put("Curazao", "CW");
            byName.put(Strings.simplify("Curazao"), "CW");
            //
            byName.put("Brunei Darussalam", "BN");
            byName.put(Strings.simplify("Brunei Darussalam"), "BN");
            //
            byName.put("Sint Maarten", "SX");
            byName.put(Strings.simplify("Sint Maarten"), "SX");
            //
            byName.put("Antigua and Barbuda", "AG");
            byName.put(Strings.simplify("Antigua and Barbuda"), "AG");
            //
            byName.put("Czech Republic", "CZ");
            byName.put(Strings.simplify("Czech Republic"), "CZ");
            //Saint Lucia
            byName.put("Saint Lucia", "LC");
            byName.put(Strings.simplify("Saint Lucia"), "LC");
            //https://www.iban.com/country-codes
            byName.put("ALBANIA", "AL");
            byName.put("ANDORRA", "AD");
            byName.put("ANTIGUA AND BARBUDA", "AG");
            byName.put("ARGENTINA", "AR");
            byName.put("ARUBA", "AW");
            byName.put("AUSTRALIA", "AU");
            byName.put("AUSTRIA", "AT");
            byName.put("AZERBAIJAN", "AZ");
            byName.put("BAHAMAS", "BS");
            byName.put("BAHRAIN", "BH");
            byName.put("BARBADOS", "BB");
            byName.put("BELGIUM", "BE");
            byName.put("BELIZE", "BZ");
            byName.put("BERMUDA", "BM");
            byName.put("BRAZIL", "BR");
            byName.put("BRUNEI DARUSSALAM", "BN");
            byName.put("BULGARIA", "BG");
            byName.put("CANADA", "CA");
            byName.put("CAYMAN ISLANDS", "KY");
            byName.put("CHILE", "CL");
            byName.put("CHINA", "CN");
            byName.put("COLOMBIA", "CO");
            byName.put("COOK ISLANDS", "CK");
            byName.put("COSTA RICA", "CR");
            byName.put("CROATIA", "HR");
            byName.put("CURACAO", "CW");
            byName.put("CYPRUS", "CY");
            byName.put("CZECHIA", "CZ");
            byName.put("DENMARK", "DK");
            byName.put("DOMINICA", "DM");
            byName.put("ECUADOR", "EC");
            byName.put("ESTONIA", "EE");
            byName.put("FAROE ISLANDS", "FO");
            byName.put("FINLAND", "FI");
            byName.put("FRANCE", "FR");
            byName.put("GERMANY", "DE");
            byName.put("GHANA", "GH");
            byName.put("GIBRALTAR", "GI");
            byName.put("GREECE", "GR");
            byName.put("GUERNSEY", "GG");
            byName.put("HONG KONG", "HK");
            byName.put("HUNGARY", "HU");
            byName.put("ICELAND", "IS");
            byName.put("INDIA", "IN");
            byName.put("INDONESIA", "ID");
            byName.put("IRELAND", "IE");
            byName.put("ISLE OF MAN", "IM");
            byName.put("ISRAEL", "IL");
            byName.put("ITALY", "IT");
            byName.put("JAMAICA", "JM");
            byName.put("JAPAN", "JP");
            byName.put("JERSEY", "JE");
            byName.put("KAZAKHSTAN", "KZ");
            byName.put("KOREA, REPUBLIC OF", "KR");
            byName.put("KUWAIT", "KW");
            byName.put("LATVIA", "LV");
            byName.put("LEBANON", "LB");
            byName.put("LIECHTENSTEIN", "LI");
            byName.put("LITHUANIA", "LT");
            byName.put("LUXEMBOURG", "LU");
            byName.put("MACAO", "MO");
            byName.put("MALAYSIA", "MY");
            byName.put("MALDIVES", "MV");
            byName.put("MALTA", "MT");
            byName.put("MAURITIUS", "MU");
            byName.put("MEXICO", "MX");
            byName.put("MONACO", "MC");
            byName.put("NETHERLANDS", "NL");
            byName.put("NEW CALEDONIA", "NC");
            byName.put("NEW ZEALAND", "NZ");
            byName.put("NIGERIA", "NG");
            byName.put("NORWAY", "NO");
            byName.put("OMAN", "OM");
            byName.put("PAKISTAN", "PK");
            byName.put("PANAMA", "PA");
            byName.put("PERU", "PE");
            byName.put("POLAND", "PL");
            byName.put("PORTUGAL", "PT");
            byName.put("QATAR", "QA");
            byName.put("ROMANIA", "RO");
            byName.put("RUSSIAN FEDERATION", "RU");
            byName.put("SAINT KITTS AND NEVIS", "KN");
            byName.put("SAINT LUCIA", "LC");
            byName.put("SAN MARINO", "MF");
            byName.put("SAINT VINCENT AND THE GRENADINES", "VC");
            byName.put("SAUDI ARABIA", "SA");
            byName.put("SEYCHELLES", "SC");
            byName.put("SINGAPORE", "SG");
            byName.put("SLOVAKIA", "SK");
            byName.put("SLOVENIA", "SI");
            byName.put("SOUTH AFRICA", "ZA");
            byName.put("SPAIN", "ES");
            byName.put("SWEDEN", "SE");
            byName.put("SWITZERLAND", "CH");
            byName.put("TURKIYE", "TR");
            byName.put("TURKS AND CAICOS ISLANDS", "TC");
            byName.put("UNITED ARAB EMIRATES", "AE");
            byName.put("UNITED KINGDOM OF GREAT BRITAIN AND NORTHERN IRELAND", "GB");
            byName.put("URUGUAY", "UY");
            byName.put("VANUATU", "VU");
            byName.put("VIRGIN ISLANDS, BRITISH", "VG");
            byName.put("HONGKONG", "HK");
            byName.put("BRUNEIDARUSSALAM", "BN");
            Map<String, String> map = new HashMap<>();
            for (String name : byName.keySet()) {
                map.put(name.replaceAll("\\s+", ""), byName.get(name));
            }
            byName.putAll(map);
        }
        byName = Collections.unmodifiableMap(byName);
        byIso = Collections.unmodifiableMap(byIso);
    }

    public String byIsoFull(String iso) {
        return iso + ": " + byIso.get(iso);
    }

    public String byIso(String iso) {
        return iso != null ? byIso.get(iso) : null;
    }

    public String byNameFull(String name) {
        return byName.get(name) + ": " + name;
    }

    public String byName(String name) {
        return byName.get(name);
    }

}

