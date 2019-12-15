package org.aoju.bus.extra.locale;

/**
 * @author Kimi Liu
 * @version 5.3.2
 * @since JDK 1.8+
 */
public class SJIS extends Verifier {

    static int[] cclass;
    static int[] states;
    static int stFactor;
    static String charset;

    public SJIS() {
        cclass = new int[256 / 8];

        cclass[0] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (0)))))))));
        cclass[1] = ((((((((((((0) << 4) | (0)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[2] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[3] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((0) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[4] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[5] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[6] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[7] = ((((((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1))))))) << 16) | (((((((((1) << 4) | (1)))) << 8) | (((((1) << 4) | (1)))))))));
        cclass[8] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[9] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[10] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[11] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[12] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[13] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[14] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[15] = ((((((((((((1) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[16] = ((((((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3))))))) << 16) | (((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[17] = ((((((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3))))))) << 16) | (((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[18] = ((((((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3))))))) << 16) | (((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[19] = ((((((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3))))))) << 16) | (((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[20] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (4)))))))));
        cclass[21] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[22] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[23] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[24] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[25] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[26] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[27] = ((((((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2))))))) << 16) | (((((((((2) << 4) | (2)))) << 8) | (((((2) << 4) | (2)))))))));
        cclass[28] = ((((((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3))))))) << 16) | (((((((((3) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[29] = ((((((((((((4) << 4) | (4)))) << 8) | (((((4) << 4) | (5))))))) << 16) | (((((((((5) << 4) | (3)))) << 8) | (((((3) << 4) | (3)))))))));
        cclass[30] = ((((((((((((4) << 4) | (4)))) << 8) | (((((4) << 4) | (4))))))) << 16) | (((((((((4) << 4) | (4)))) << 8) | (((((4) << 4) | (4)))))))));
        cclass[31] = ((((((((((((0) << 4) | (0)))) << 8) | (((((0) << 4) | (4))))))) << 16) | (((((((((4) << 4) | (4)))) << 8) | (((((4) << 4) | (4)))))))));

        states = new int[3];

        states[0] = ((((((((((((eError) << 4) | (eError)))) << 8) | (((((eError) << 4) | (eError))))))) << 16) | (((((((((3) << 4) | (eStart)))) << 8) | (((((eStart) << 4) | (eError)))))))));
        states[1] = ((((((((((((eItsMe) << 4) | (eItsMe)))) << 8) | (((((eItsMe) << 4) | (eItsMe))))))) << 16) | (((((((((eError) << 4) | (eError)))) << 8) | (((((eError) << 4) | (eError)))))))));
        states[2] = ((((((((((((eStart) << 4) | (eStart)))) << 8) | (((((eStart) << 4) | (eStart))))))) << 16) | (((((((((eError) << 4) | (eError)))) << 8) | (((((eItsMe) << 4) | (eItsMe)))))))));

        charset = "Shift_JIS";
        stFactor = 6;
    }

    public int[] cclass() {
        return cclass;
    }

    public int[] states() {
        return states;
    }

    public int stFactor() {
        return stFactor;
    }

    public String charset() {
        return charset;
    }

    public boolean isUCS2() {
        return false;
    }

}
