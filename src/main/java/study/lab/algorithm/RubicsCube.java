package study.lab.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class RubicsCube {

    private int[] method;
    private int tailIndex;
    private Map<String, Object> answers = new LinkedHashMap<String, Object>(1, 0.75f, true){
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > 1;
        }
    };

    private Block[] cube;

    private static final int MAX_STEP = 11;

    private long count;

    RubicsCube() {
        cube = new Block[]{
                new Block(0, Color.GREEN.getValue(), Color.ORANGE.getValue(), Color.YELLOW.getValue()),
                new Block(1, Color.RED.getValue(), Color.YELLOW.getValue(), Color.GREEN.getValue()),
                new Block(2, Color.RED.getValue(), Color.BLUE.getValue(), Color.YELLOW.getValue()),
                new Block(3, Color.ORANGE.getValue(), Color.GREEN.getValue(), Color.WHITE.getValue()),
                new Block(4, Color.WHITE.getValue(), Color.BLUE.getValue(), Color.RED.getValue()),
                new Block(5, Color.BLUE.getValue(), Color.YELLOW.getValue(), Color.ORANGE.getValue()),
                new Block(6, Color.ORANGE.getValue(), Color.WHITE.getValue(), Color.BLUE.getValue()),
                new Block(7, Color.WHITE.getValue(), Color.RED.getValue(), Color.GREEN.getValue()),
        };
        System.out.println("-------init-------");
        printStatus();
        System.out.println("------------------");

        method = new int[MAX_STEP];
        for (int i = 0; i<method.length; i++) {
            method[i] = -1;
        }
        count = 0L;
    }

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        RubicsCube rc = new RubicsCube();
//        rc.turn(0);
//        rc.turn(1);
//        rc.turn(2);
//        rc.printStatus();
        rc.solve(-1);
        rc.printAnswer();
        System.out.println("time cost:" + String.valueOf(System.currentTimeMillis() - time));
    }

    private void printAnswer() {
        System.out.println("have tried " + count + " steps! Got Answer:");
        for (String s : answers.keySet()) {
            System.out.println(s);
        }
    }

    public void solve(int lastMethod) {
        if (test()) {
            printMethod();
        } else {
            if (method[MAX_STEP-1] != -1) {
                return;
            }
            /*for (int i = 0; i < 18; i++) {
                if (lastMethod == i || Math.abs(lastMethod - i)%6 == 0) {
                    continue;
                }*/
            for (int i = 0; i < 9; i++) {
                if (lastMethod == i || Math.abs(lastMethod - i)%3 == 0) {
                    continue;
                }
                turn(i);
                count++;

//                printStatus();
                method[tailIndex] = i;
                tailIndex++;
                solve(i);
                tailIndex--;
                method[tailIndex] = -1;
                restore(i);
            }
        }
    }

    private void printStatus() {
        System.out.println("   "+getLabel(cube[4].getColor()[0])+getLabel(cube[5].getColor()[0])+"   ");
        System.out.println("   "+getLabel(cube[6].getColor()[0])+getLabel(cube[7].getColor()[0])+"   ");

        System.out.println(getLabel(cube[4].getColor()[1])
                +getLabel(cube[6].getColor()[1])+" "
                +getLabel(cube[6].getColor()[2])
                +getLabel(cube[7].getColor()[2])+" "
                +getLabel(cube[7].getColor()[1])
                +getLabel(cube[5].getColor()[1])
        );
        System.out.println(getLabel(cube[0].getColor()[1])
                +getLabel(cube[2].getColor()[1])+" "
                +getLabel(cube[2].getColor()[2])
                +getLabel(cube[3].getColor()[2])+" "
                +getLabel(cube[3].getColor()[1])
                +getLabel(cube[1].getColor()[1])
        );

        System.out.println("   "+getLabel(cube[2].getColor()[0])+getLabel(cube[3].getColor()[0])+"   ");
        System.out.println("   "+getLabel(cube[0].getColor()[0])+getLabel(cube[1].getColor()[0])+"   ");

        System.out.println("   "+getLabel(cube[0].getColor()[2])+getLabel(cube[1].getColor()[2])+"   ");
        System.out.println("   "+getLabel(cube[4].getColor()[2])+getLabel(cube[5].getColor()[2])+"   ");
//        System.out.println("----------");
    }

    private String getLabel(int i) {
        return Color.getByValue(i).name().substring(0, 1);
    }

    /*private void restore(int i) {
        int m = i;
        if (i < 12) {
            m = i < 6 ? i + 6 : i - 6;
        }
        turn(m);
    }*/

    private void restore(int i) {
        int m = i;
        if (i < 6) {
            m = i < 3 ? i + 3 : i - 3;
        }
        turn(m);
    }

    private void turn(int i) {
        if (i == M.U.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[2].turn(1);
                cube[3].turn(1);
                cube[6].turn(1);
                cube[7].turn(1);
            }
            Block tmp = cube[2];
            cube[2] = cube[3];
            cube[3] = cube[7];
            cube[7] = cube[6];
            cube[6] = tmp;
        /*} else if (i == M.D.getValue()) {
            cube[0].turn(1);
            cube[1].turn(1);
            cube[4].turn(1);
            cube[5].turn(1);

            Block tmp = cube[0];
            cube[0] = cube[4];
            cube[4] = cube[5];
            cube[5] = cube[1];
            cube[1] = tmp;
        } else if (i == M.L.getValue()) {
            cube[0].turn(0);
            cube[2].turn(0);
            cube[4].turn(0);
            cube[6].turn(0);

            Block tmp = cube[0];
            cube[0] = cube[2];
            cube[2] = cube[6];
            cube[6] = cube[4];
            cube[4] = tmp;*/
        } else if (i == M.R.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[1].turn(0);
                cube[3].turn(0);
                cube[5].turn(0);
                cube[7].turn(0);
            }

            Block tmp = cube[1];
            cube[1] = cube[5];
            cube[5] = cube[7];
            cube[7] = cube[3];
            cube[3] = tmp;
        } else if (i == M.F.getValue()) {
            cube[0].turn(2);
            cube[1].turn(2);
            cube[2].turn(2);
            cube[3].turn(2);

            Block tmp = cube[0];
            cube[0] = cube[1];
            cube[1] = cube[3];
            cube[3] = cube[2];
            cube[2] = tmp;
        /*} else if (i == M.B.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[4].turn(2);
                cube[5].turn(2);
                cube[6].turn(2);
                cube[7].turn(2);
            }

            Block tmp = cube[4];
            cube[4] = cube[6];
            cube[6] = cube[7];
            cube[7] = cube[5];
            cube[5] = tmp;*/
        } else if (i == M.U1.getValue()) {
            cube[2].turn(1);
            cube[3].turn(1);
            cube[6].turn(1);
            cube[7].turn(1);

            Block tmp = cube[2];
            cube[2] = cube[6];
            cube[6] = cube[7];
            cube[7] = cube[3];
            cube[3] = tmp;
        /*} else if (i == M.D1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(1);
                cube[1].turn(1);
                cube[4].turn(1);
                cube[5].turn(1);
            }

            Block tmp = cube[0];
            cube[0] = cube[1];
            cube[1] = cube[5];
            cube[5] = cube[4];
            cube[4] = tmp;
        } else if (i == M.L1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(0);
                cube[2].turn(0);
                cube[4].turn(0);
                cube[6].turn(0);
            }

            Block tmp = cube[0];
            cube[0] = cube[4];
            cube[4] = cube[6];
            cube[6] = cube[2];
            cube[2] = tmp;*/
        } else if (i == M.R1.getValue()) {
            cube[1].turn(0);
            cube[3].turn(0);
            cube[5].turn(0);
            cube[7].turn(0);

            Block tmp = cube[1];
            cube[1] = cube[3];
            cube[3] = cube[7];
            cube[7] = cube[5];
            cube[5] = tmp;
        } else if (i == M.F1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(2);
                cube[1].turn(2);
                cube[2].turn(2);
                cube[3].turn(2);
            }

            Block tmp = cube[0];
            cube[0] = cube[2];
            cube[2] = cube[3];
            cube[3] = cube[1];
            cube[1] = tmp;
        /*} else if (i == M.B1.getValue()) {
            cube[4].turn(2);
            cube[5].turn(2);
            cube[6].turn(2);
            cube[7].turn(2);

            Block tmp = cube[4];
            cube[4] = cube[5];
            cube[5] = cube[7];
            cube[7] = cube[6];
            cube[6] = tmp;*/
        } else if (i == M.U2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[2].turn(1);
                cube[3].turn(1);
                cube[6].turn(1);
                cube[7].turn(1);
            }

            Block tmp = cube[2];
            cube[2] = cube[7];
            cube[7] = tmp;
            tmp = cube[3];
            cube[3] = cube[6];
            cube[6] = tmp;
        /*} else if (i == M.D2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(1);
                cube[1].turn(1);
                cube[4].turn(1);
                cube[5].turn(1);
            }

            Block tmp = cube[0];
            cube[0] = cube[5];
            cube[5] = tmp;
            tmp = cube[1];
            cube[1] = cube[4];
            cube[4] = tmp;
        } else if (i == M.L2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(0);
                cube[2].turn(0);
                cube[4].turn(0);
                cube[6].turn(0);
            }

            Block tmp = cube[0];
            cube[0] = cube[6];
            cube[6] = tmp;
            tmp = cube[2];
            cube[2] = cube[4];
            cube[4] = tmp;*/
        } else if (i == M.R2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[1].turn(0);
                cube[3].turn(0);
                cube[5].turn(0);
                cube[7].turn(0);
            }

            Block tmp = cube[1];
            cube[1] = cube[7];
            cube[7] = tmp;
            tmp = cube[3];
            cube[3] = cube[5];
            cube[5] = tmp;
        } else if (i == M.F2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(2);
                cube[1].turn(2);
                cube[2].turn(2);
                cube[3].turn(2);
            }

            Block tmp = cube[0];
            cube[0] = cube[3];
            cube[3] = tmp;
            tmp = cube[1];
            cube[1] = cube[2];
            cube[2] = tmp;
        /*} else if (i == M.B2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[4].turn(2);
                cube[5].turn(2);
                cube[6].turn(2);
                cube[7].turn(2);
            }

            Block tmp = cube[4];
            cube[4] = cube[7];
            cube[7] = tmp;
            tmp = cube[5];
            cube[5] = cube[6];
            cube[6] = tmp;*/
        }
    }

    private void printMethod() {
//        System.out.println("Done!");
        StringBuilder sb = new StringBuilder();
        for (int i : method) {
            if (i == -1) {
                break;
            }
            sb.append(",").append(M.getByValue(i).name());
        }
        String key = sb.toString().substring(1);
        boolean needPut = true;
        for (String k : answers.keySet()) {
            if (key.length() >= k.length()) {
                needPut = false;
                break;
            }
        }
        if (needPut) {
            answers.put(key, 1);
        }

    }

    public boolean test() {
        if (cube[0].getColor()[0] == cube[1].getColor()[0] && cube[1].getColor()[0] == cube[2].getColor()[0] && cube[2].getColor()[0] == cube[3].getColor()[0]
                &&
                cube[4].getColor()[0] == cube[5].getColor()[0] && cube[5].getColor()[0] == cube[6].getColor()[0] && cube[6].getColor()[0] == cube[7].getColor()[0]
                &&
                cube[0].getColor()[1] == cube[2].getColor()[1] && cube[2].getColor()[1] == cube[4].getColor()[1] && cube[4].getColor()[1] == cube[6].getColor()[1]
                &&
                cube[1].getColor()[1] == cube[3].getColor()[1] && cube[3].getColor()[1] == cube[5].getColor()[1] && cube[5].getColor()[1] == cube[7].getColor()[1]
                &&
                cube[0].getColor()[2] == cube[1].getColor()[2] && cube[1].getColor()[2] == cube[4].getColor()[2] && cube[4].getColor()[2] == cube[5].getColor()[2]
                &&
                cube[2].getColor()[2] == cube[3].getColor()[2] && cube[3].getColor()[2] == cube[6].getColor()[2] && cube[6].getColor()[2] == cube[7].getColor()[2]) {
            return true;
        }
        return false;
    }

    static class Block {
        private int pos;
        private int[] color = new int[3];

        public Block(int pos, int xy, int yz, int zx) {
            this.pos = pos;
            color[0] = xy;
            color[1] = yz;
            color[2] = zx;
        }

        public void turn(int axis) {
            if (axis == 0) {
                changeValue(0, 2);
            } else if (axis == 1) {
                changeValue(0, 1);
            } else if (axis == 2) {
                changeValue(1, 2);
            }
        }

        private void changeValue(int i, int i1) {
            int tmp = color[i];
            color[i] = color[i1];
            color[i1] = tmp;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int[] getColor() {
            return color;
        }

        public void setColor(int[] color) {
            this.color = color;
        }
    }

    enum Color {
        WHITE(0), YELLOW(1), RED(2), GREEN(3), BLUE(4), ORANGE(5);
        private int value;

        Color(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Color getByValue(int value) {
            for (Color c : values()) {
                if (c.value == value) {
                    return c;
                }
            }
            return null;
        }
    }

    enum M {
        /*U(0), D(1), L(2), R(3), F(4), B(5),
        U1(6), D1(7), L1(8), R1(9), F1(10), B1(11),
        U2(12), D2(13), L2(14), R2(15), F2(16), B2(17);*/

        U(0), R(1), F(2), U1(3), R1(4), F1(5),
        U2(6), R2(7), F2(8);

        private int value;
        M(int i) {
            value = i;
        }

        public int getValue() {
            return value;
        }

        public static M getByValue(int value) {
            for (M m : values()) {
                if (m.getValue() == value) {
                    return m;
                }
            }
            return null;
        }
    }
}
