/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jb.licht.klassen;

import org.json.JSONObject;

/**
 *
 * @author Jan
 */
public class Instelling {

    public static final String cInstelling = "instelling";
    public static final String cLokatie = "lokatie";
    public static final String cLengte = "lengte";
    public static final String cBreedte = "breedte";
    public static final String cLichtUit = "lichtuit";
    public static final String cTijdStip = "tijdstip";
    public static final String cPeriode = "periode";
    public static final String cSensor = "sensor";
    public static final String cGrens = "grens";
    public static final String cDrempel = "drempel";
    public static final String cMax = "max";
    public static final String cInterval = "interval";
    public static final String cHerhaling = "herhaling";

    public static final int cResultOK = 0;
    public static final int cResultFout = 9;

    private double mLengte;
    private double mBreedte;
    private int mLichtUitUur;
    private int mLichtUitMin;
    private int mUitTijd;
    private int mSensorGrens;
    private int mSensorDrempel;
    private int mMaxSensor;
    private int mPeriodeDonker;
    private int mPeriodeMinuut;
    private int mPeriodeSec;

    public double xLengte() {
        return mLengte;
    }

    public int xLengte(double pLengte) {
        if (pLengte > 360d) {
            return cResultFout;
        }
        if (pLengte < -360d) {
            return cResultFout;
        }

        mLengte = pLengte;
        return cResultOK;
    }

    public double xBreedte() {
        return mBreedte;
    }

    public int xBreedte(double pBreedte) {
        if (pBreedte > 90d) {
            return cResultFout;
        }
        if (pBreedte < -90d) {
            return cResultFout;
        }

        mBreedte = pBreedte;
        return cResultOK;
    }

    public String xLichtUit() {
        return String.format("%02d", mLichtUitUur) + ":" + String.format("%02d", mLichtUitMin);
    }

    public int xLichtUit(String pLichtUit) {
        int lLichtUitUur = 0;
        int lLichtUitMin = 0;
        int lResult;

        lResult = cResultFout;

        if (pLichtUit.length() == 5) {
            if (pLichtUit.substring(2, 3).equals(":")) {
                try {
                    lLichtUitUur = Integer.parseInt(pLichtUit.substring(0, 2));
                    lLichtUitMin = Integer.parseInt(pLichtUit.substring(3));
                    if (lLichtUitUur >= 0) {
                        if (lLichtUitUur < 24) {
                            if (lLichtUitMin >= 0) {
                                if (lLichtUitMin < 60) {
                                    lResult = cResultOK;
                                }
                            }
                        }
                    }
                } catch (NumberFormatException pExc) {
                }
            }
        }

        if (lResult == cResultOK) {
            mLichtUitUur = lLichtUitUur;
            mLichtUitMin = lLichtUitMin;
        }
        return lResult;
    }

    public int xLichtUitUur() {
        return mLichtUitUur;
    }

    public int xLichtUitUur(int pUur) {
        if (pUur > 23) {
            return cResultFout;
        }
        if (pUur < 0) {
            return cResultFout;
        }

        mLichtUitUur = pUur;
        return cResultOK;
    }

    public int xLichtUitMin() {
        return mLichtUitMin;
    }

    public int xLichtUitMin(int pMin) {
        if (pMin > 59) {
            return cResultFout;
        }
        if (pMin < 0) {
            return cResultFout;
        }

        mLichtUitMin = pMin;
        return cResultOK;
    }

    public int xUitTijd() {
        return mUitTijd;
    }

    public int xUitTijd(int pPeriode) {
        if (pPeriode > 120) {
            return cResultFout;
        }
        if (pPeriode < 0) {
            return cResultFout;
        }

        mUitTijd = pPeriode;
        return cResultOK;
    }

    public int xSensorGrens() {
        return mSensorGrens;
    }

    public int xSensorGrens(int pGrens) {
        if (pGrens > 500) {
            return cResultFout;
        }
        if (pGrens < 0) {
            return cResultFout;
        }

        mSensorGrens = pGrens;
        return cResultOK;
    }

    public int xSensorDrempel() {
        return mSensorDrempel;
    }

    public int xSensorDrempel(int pDrempel) {
        if (pDrempel > 10) {
            return cResultFout;
        }
        if (pDrempel < 0) {
            return cResultFout;
        }

        mSensorDrempel = pDrempel;
        return cResultOK;
    }

    public int xMaxSensor() {
        return mMaxSensor;
    }

    public int xMaxSensor(int pMax) {
        if (pMax > 1000) {
            return cResultFout;
        }
        if (pMax < 0) {
            return cResultFout;
        }

        mMaxSensor = pMax;
        return cResultOK;
    }

    public int xPeriodeDonker() {
        return mPeriodeDonker;
    }

    public int xPeriodeDonker(int pPeriode) {
        if (pPeriode > 10) {
            return cResultFout;
        }
        if (pPeriode < 0) {
            return cResultFout;
        }

        mPeriodeDonker = pPeriode;
        return cResultOK;
    }

    public int xPeriode() {
        return (mPeriodeMinuut * 60) + mPeriodeSec;
    }

    public int xPeriodeMinuut() {
        return mPeriodeMinuut;
    }

    public int xPeriodeSec() {
        return mPeriodeSec;
    }

    public int xPeriodeSec(int pSec) {
        if (pSec > 300) {
            return cResultFout;
        }
        if (pSec < 0) {
            return cResultFout;
        }

        mPeriodeSec = pSec;
        return cResultOK;
    }

    public JSONObject xInstelling() {
        JSONObject lInstelling;
        JSONObject lLokatie;
        JSONObject lLichtUit;
        JSONObject lSensor;
        String lTijdStip;
        int lPeriode;

        lInstelling = new JSONObject();
        lLokatie = new JSONObject();
        lLokatie.put(cLengte, mLengte);
        lLokatie.put(cBreedte, mBreedte);
        lInstelling.put(cLokatie, lLokatie);
        lLichtUit = new JSONObject();
        lTijdStip = String.format("%02d", mLichtUitUur) + ":" + String.format("%02d", mLichtUitMin);
        lLichtUit.put(cTijdStip, lTijdStip);
        lLichtUit.put(cPeriode, mUitTijd);
        lInstelling.put(cLichtUit, lLichtUit);
        lSensor = new JSONObject();
        lSensor.put(cGrens, mSensorGrens);
        lSensor.put(cDrempel, mSensorDrempel);
        lSensor.put(cMax, mMaxSensor);
        lPeriode = (mPeriodeMinuut * 60) + mPeriodeSec;
        lSensor.put(cInterval, lPeriode);
        lSensor.put(cHerhaling, mPeriodeDonker);
        lInstelling.put(cSensor, lSensor);

        return lInstelling;
    }

    public Instelling() {
        mLengte = 4.0;
        mBreedte = 52.0;
        mLichtUitUur = 23;
        mLichtUitMin = 0;
        mUitTijd = 30;
        mSensorGrens = 25;
        mSensorDrempel = 2;
        mMaxSensor = 1000;
        mPeriodeDonker = 3;
        mPeriodeMinuut = 1;
        mPeriodeSec = 0;
    }

    public Instelling(double pLengte, double pBreedte, int pLichtUitUur, int pLichtUitMin, int pUitTijd, int pSensorGrens, int pSensorDrempel, int pMaxSensor, int pPeriodeDonker, int pPeriodeMinuut, int pPeriodeSec) {
        mLengte = pLengte;
        mBreedte = pBreedte;
        mLichtUitUur = pLichtUitUur;
        mLichtUitMin = pLichtUitMin;
        mUitTijd = pUitTijd;
        mSensorGrens = pSensorGrens;
        mSensorDrempel = pSensorDrempel;
        mMaxSensor = pMaxSensor;
        mPeriodeDonker = pPeriodeDonker;
        mPeriodeMinuut = 0;
        mPeriodeSec = (pPeriodeMinuut * 60) + pPeriodeSec;
    }

    public Instelling(JSONObject pInstelling) {
        JSONObject lLokatie;
        JSONObject lLichtUit;
        JSONObject lSensor;
        String lLicht;
        int lResult;

        lLokatie = pInstelling.optJSONObject(cLokatie);
        lLichtUit = pInstelling.optJSONObject(cLichtUit);
        lSensor = pInstelling.optJSONObject(cSensor);

        if (lLokatie == null) {
            mLengte = 0d;
            mBreedte = 0d;
        } else {
            mLengte = lLokatie.optDouble(cLengte, 0);
            mBreedte = lLokatie.optDouble(cBreedte, 0);
        }

        if (lLichtUit == null) {
            mLichtUitUur = 0;
            mLichtUitMin = 0;
        } else {
            lLicht = lLichtUit.optString(cTijdStip, "");
            lResult = xLichtUit(lLicht);
            if (lResult != cResultOK) {
                mLichtUitUur = 0;
                mLichtUitMin = 0;
            }
        }
        mUitTijd = lLichtUit.optInt(cPeriode, 0);

        mSensorGrens = lSensor.optInt(cGrens);
        mSensorDrempel = lSensor.optInt(cDrempel);
        mMaxSensor = lSensor.optInt(cMax);
        mPeriodeSec = lSensor.optInt(cInterval);
        mPeriodeMinuut = 0;
        mPeriodeDonker = lSensor.optInt(cHerhaling);
    }
}
