/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb.licht.klassen;

import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jan
 */
public class Schakelaar {

    public static final String cVolgNummer = "volgnummer";
    public static final String cNaam = "naam";
    public static final String cType = "type";
    public static final String cGroep = "groep";
    public static final String cPunt = "punt";
    public static final String cPauze = "pauze";
    public static final String cIP = "ip";
    public static final String cAktief = "aktief";

    public static final int ResultOK = 0;
    public static final int ResultOnjuisteWaarde = 8;
    public static final int ResultNietOndersteund = 9;
    public static final int ResultNaamFout = 10;
    public static final int ResultTypeFout = 20;
    public static final int ResultGroepFout = 30;
    public static final int ResultPuntFout = 40;
    public static final int ResultIpFout = 50;
    public static final int ResultPauzeFout = 60;

    public static final String[] Types = {"kaku", "elro", "esp"};

    private int mVolgNummer;
    private String mNaam;
    private boolean mAktief;
    private String mType;
    private String mGroep;
    private String mPunt;
    private int mPauze;
    private String mIP;

    public Schakelaar(int pVolgNummer, String pNaam, boolean pAktief, String pType, String pGroep, String pPunt, int pPauze, String pIP) {
        mVolgNummer = pVolgNummer;
        mNaam = pNaam;
        mAktief = pAktief;
        mType = pType;
        mGroep = pGroep;
        mPunt = pPunt;
        mPauze = pPauze;
        mIP = pIP;
    }

    public Schakelaar(JSONObject pSchakelaar) {
        mVolgNummer = pSchakelaar.optInt(cVolgNummer, 0);
        mNaam = pSchakelaar.optString(cNaam, "");
        mType = pSchakelaar.optString(cType, "");
        mGroep = pSchakelaar.optString(cGroep, "");
        mPunt = pSchakelaar.optString(cPunt, "");
        mPauze = pSchakelaar.optInt(cPauze, 0);
        mIP = pSchakelaar.optString(cIP, "");
        mAktief = pSchakelaar.optBoolean(cAktief, false);
    }

    public Schakelaar() {
        mVolgNummer = 0;
        mNaam = "";
        mAktief = false;
        mType = "";
        mGroep = "";
        mPunt = "";
        mPauze = 0;
        mIP = "";
    }

    public JSONObject xSchakelaar() {
        JSONObject lSchakelaar;

        lSchakelaar = new JSONObject();
        try {
            lSchakelaar.put(cVolgNummer, mVolgNummer);
            lSchakelaar.put(cNaam, mNaam);
            lSchakelaar.put(cType, mType);
            if (!mGroep.equals("")) {
                lSchakelaar.put(cGroep, mGroep);
            }
            if (!mPunt.equals("")) {
                lSchakelaar.put(cPunt, mPunt);
            }
            lSchakelaar.put(cPauze, mPauze);
            if (!mIP.equals("")) {
                lSchakelaar.put(cIP, mIP);
            }
            lSchakelaar.put(cAktief, mAktief);
        } catch (JSONException pExc) {
        }

        return lSchakelaar;
    }

    public int xVolgNummer() {
        return mVolgNummer;
    }

    public int xVolgNummer(int pVolgNummer) {
        if (pVolgNummer < 0) {
            return ResultOnjuisteWaarde;
        } else {
            mVolgNummer = pVolgNummer;
            return ResultOK;
        }
    }

    public String xNaam() {
        return mNaam;
    }

    public int xNaam(String pNaam) {
        if (pNaam.equals("")) {
            return ResultOnjuisteWaarde;
        } else {
            mNaam = pNaam;
            return ResultOK;
        }
    }

    public boolean xAktief() {
        return mAktief;
    }

    public int xAktief(boolean pAktief) {
        mAktief = pAktief;
        return ResultOK;
    }

    public String xType() {
        return mType;
    }

    public int xType(String pType) {
        int lResult;

        if (pType.equals("")) {
            lResult = ResultOK;
        } else {
            if (Arrays.asList(Types).contains(pType)) {
                lResult = ResultOK;
            } else {
                lResult = ResultNietOndersteund;
            }
        }
        if (lResult == ResultOK) {
            mType = pType;
        }
        return lResult;
    }

    public String xGroep() {
        return mGroep;
    }

    public int xGroep(String pGroep) {
        int lResult;

        if (pGroep.equals("")) {
            lResult = ResultOK;
        } else {
            lResult = sTestLetter(pGroep, "A", "D");
            if (lResult != ResultOK) {
                lResult = sTestGetal(pGroep, 1, 31);
            }
        }
        if (lResult == ResultOK) {
            mGroep = pGroep;
        }
        return lResult;
    }

    public String xPunt() {
        return mPunt;
    }

    public int xPunt(String pPunt) {
        int lResult;

        if (pPunt.equals("")) {
            lResult = ResultOK;
        } else {
            lResult = sTestLetter(pPunt, "A", "D");
            if (lResult != ResultOK) {
                lResult = sTestGetal(pPunt, 1, 31);
            }
        }
        if (lResult == ResultOK) {
            mPunt = pPunt;
        }
        return lResult;
    }

    public int xPauze() {
        return mPauze;
    }

    public int xPauze(int pPauze) {
        int lResult;

        lResult = sTestIntReeks(pPauze, 0, 1000);
        if (lResult == ResultOK) {
            mPauze = pPauze;
        }
        return lResult;
    }

    public String xIP() {
        return mIP;
    }

    public int xIP(String pIP) {
        int lResult;

        if (pIP.equals("")) {
            lResult = ResultOK;
        } else {
            lResult = sTestIP(pIP);
        }
        if (lResult == ResultOK) {
            mIP = pIP;
        }
        return lResult;
    }

    public boolean xIsGelijk(Schakelaar pSchakelaar) {
        boolean lGelijk;

        lGelijk = false;
        if (mVolgNummer == pSchakelaar.xVolgNummer()) {
            if (mNaam.equals(pSchakelaar.xNaam())) {
                if (mAktief == pSchakelaar.xAktief()) {
                    if (mType.equals(pSchakelaar.xType())) {
                        if (mGroep.equals(pSchakelaar.xGroep())) {
                            if (mPunt.equals(pSchakelaar.xPunt())) {
                                if (mIP.equals(pSchakelaar.xIP())) {
                                    if (mPauze == pSchakelaar.xPauze()) {
                                        lGelijk = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return lGelijk;
    }

    public int xTestSchakelaar() {
        int lResult;

        if (mAktief) {
            if (mNaam.length() > 0) {
                lResult = sTestIntReeks(mPauze, 0, 1000);
                if (lResult == ResultOK) {
                    switch (mType) {
                        case "kaku":
                            lResult = sTestLetter(mGroep, "A", "D");
                            if (lResult == ResultOK) {
                                lResult = sTestGetal(mPunt, 1, 3);
                                if (lResult != ResultOK) {
                                    lResult = ResultPuntFout;
                                }
                            } else {
                                lResult = ResultGroepFout;
                            }
                            break;
                        case "elro":
                            lResult = sTestGetal(mGroep, 1, 31);
                            if (lResult == ResultOK) {
                                lResult = sTestLetter(mPunt, "A", "D");
                                if (lResult != ResultOK) {
                                    lResult = ResultPuntFout;
                                }
                            } else {
                                lResult = ResultGroepFout;
                            }
                            break;
                        case "esp":
                            lResult = sTestIP(mIP);
                            if (lResult != ResultOK) {
                                lResult = ResultIpFout;
                            }
                            break;
                        default:
                            lResult = ResultTypeFout;
                            break;
                    }
                } else {
                    lResult = ResultPauzeFout;
                }
            } else {
                lResult = ResultNaamFout;
            }
            if (lResult != 0) {
                mAktief = false;
            }
        } else {
            lResult = ResultOK;
        }
        return lResult;
    }

    private int sTestLetter(String pWaarde, String pMin, String pMax) {
        int lResult;

        if (pWaarde.length() > 1) {
            lResult = ResultOnjuisteWaarde;
        } else {
            if (pWaarde.compareTo(pMin) < 0) {
                lResult = ResultOnjuisteWaarde;
            } else {
                if (pWaarde.compareTo(pMax) > 0) {
                    lResult = ResultOnjuisteWaarde;
                } else {
                    lResult = ResultOK;
                }
            }
        }
        return lResult;
    }

    private int sTestGetal(String pWaarde, int pMin, int pMax) {
        int lTestInt = 0;
        boolean lNumeriek;
        int lResult;

        try {
            lTestInt = Integer.parseInt(pWaarde);
            lNumeriek = true;
        } catch (NumberFormatException pExc) {
            lNumeriek = false;
        }

        if (lNumeriek) {
            lResult = sTestIntReeks(lTestInt, pMin, pMax);
        } else {
            lResult = ResultOnjuisteWaarde;
        }
        return lResult;
    }

    private int sTestIntReeks(int pWaarde, int pMin, int pMax) {
        int lResult;

        if (pWaarde < pMin) {
            lResult = ResultOnjuisteWaarde;
        } else {
            if (pWaarde > pMax) {
                lResult = ResultOnjuisteWaarde;
            } else {
                lResult = ResultOK;
            }
        }
        return lResult;
    }

    private int sTestIP(String pIP) {
        int lResult = 0;
        int lPunt;
        int lTel;
        String lWerk;
        String[] lGetal;

        lWerk = pIP;
        lGetal = new String[4];
        lTel = 0;
        while (true) {
            lPunt = lWerk.indexOf(".");
            if (lPunt < 0) {
                if (lTel < 4) {
                    lGetal[lTel] = lWerk;
                }
                lTel++;
                break;
            }
            if (lTel < 4) {
                lGetal[lTel] = lWerk.substring(0, lPunt);
                lWerk = lWerk.substring(lPunt + 1);
                lTel++;
            } else {
                lTel = -1;
                break;
            }
        }

        if (lTel == 4) {
            for (lTel = 0; lTel < 4; lTel++) {
                lResult = sTestGetal(lGetal[lTel], 0, 255);
                if (lResult != ResultOK) {
                    break;
                }
            }
        } else {
            lResult = ResultOnjuisteWaarde;
        }

        return lResult;
    }
}
