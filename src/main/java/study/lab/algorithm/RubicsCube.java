package study.lab.algorithm;

public class RubicsCube {

    private int[] method;
    private int tailIndex;

    private Block[] cube;

    RubicsCube() {
        cube = new Block[]{
                new Block(0, Color.BLUE.getValue(), Color.BLACK.getValue(), Color.RED.getValue()),
                new Block(1, Color.BLUE.getValue(), Color.YELLOW.getValue(), Color.RED.getValue()),
                new Block(2, Color.BLACK.getValue(), Color.GREEN.getValue(), Color.ORANGE.getValue()),
                new Block(3, Color.BLACK.getValue(), Color.BLUE.getValue(), Color.ORANGE.getValue()),
                new Block(4, Color.GREEN.getValue(), Color.BLACK.getValue(), Color.RED.getValue()),
                new Block(5, Color.GREEN.getValue(), Color.YELLOW.getValue(), Color.RED.getValue()),
                new Block(6, Color.YELLOW.getValue(), Color.GREEN.getValue(), Color.ORANGE.getValue()),
                new Block(7, Color.YELLOW.getValue(), Color.BLUE.getValue(), Color.ORANGE.getValue()),
        };
        method = new int[2];
        for (int i = 0; i<method.length; i++) {
            method[i] = -1;
        }
    }

    public static void main(String[] args) {
        RubicsCube rc = new RubicsCube();
        rc.solve(-1);
    }

    public void solve(int lastMethod) {
        if (test()) {
            printMethod();
        } else {
            if (method[1] != -1) {
                return;
            }
            for (int i = 0; i < 18; i++) {
                if (lastMethod == i) {
                    continue;
                }
                turn(i);
                method[tailIndex] = i;
                tailIndex++;
                solve(i);
                tailIndex--;
                method[tailIndex] = -1;
                restore(i);
            }
        }
    }

    private void restore(int i) {
        int m = i;
        if (i < 12) {
            m = i < 6 ? i + 6 : i - 6;
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
        } else if (i == M.D.getValue()) {
            cube[0].turn(1);
            cube[1].turn(1);
            cube[4].turn(1);
            cube[5].turn(1);
        } else if (i == M.L.getValue()) {
            cube[0].turn(0);
            cube[2].turn(0);
            cube[4].turn(0);
            cube[6].turn(0);
        } else if (i == M.R.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[1].turn(0);
                cube[3].turn(0);
                cube[5].turn(0);
                cube[7].turn(0);
            }
        } else if (i == M.F.getValue()) {
            cube[0].turn(2);
            cube[1].turn(2);
            cube[2].turn(2);
            cube[3].turn(2);
        } else if (i == M.B.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[4].turn(2);
                cube[5].turn(2);
                cube[6].turn(2);
                cube[7].turn(2);
            }
        } else if (i == M.U1.getValue()) {
            cube[2].turn(1);
            cube[3].turn(1);
            cube[6].turn(1);
            cube[7].turn(1);
        } else if (i == M.D1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(1);
                cube[1].turn(1);
                cube[4].turn(1);
                cube[5].turn(1);
            }
        } else if (i == M.L1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(0);
                cube[2].turn(0);
                cube[4].turn(0);
                cube[6].turn(0);
            }
        } else if (i == M.R1.getValue()) {
            cube[1].turn(0);
            cube[3].turn(0);
            cube[5].turn(0);
            cube[7].turn(0);
        } else if (i == M.F1.getValue()) {
            for (int j = 0; j < 3; j++) {
                cube[0].turn(2);
                cube[1].turn(2);
                cube[2].turn(2);
                cube[3].turn(2);
            }
        } else if (i == M.B1.getValue()) {
            cube[4].turn(2);
            cube[5].turn(2);
            cube[6].turn(2);
            cube[7].turn(2);
        } else if (i == M.U2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[2].turn(1);
                cube[3].turn(1);
                cube[6].turn(1);
                cube[7].turn(1);
            }
        } else if (i == M.D2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(1);
                cube[1].turn(1);
                cube[4].turn(1);
                cube[5].turn(1);
            }
        } else if (i == M.L2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(0);
                cube[2].turn(0);
                cube[4].turn(0);
                cube[6].turn(0);
            }
        } else if (i == M.R2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[1].turn(0);
                cube[3].turn(0);
                cube[5].turn(0);
                cube[7].turn(0);
            }
        } else if (i == M.F2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[0].turn(2);
                cube[1].turn(2);
                cube[2].turn(2);
                cube[3].turn(2);
            }
        } else if (i == M.B2.getValue()) {
            for (int j = 0; j < 2; j++) {
                cube[4].turn(2);
                cube[5].turn(2);
                cube[6].turn(2);
                cube[7].turn(2);
            }
        }
    }

    private void printMethod() {
        System.out.println("Done!");
        for (int i : method) {
            if (i == -1) {
                break;
            }
            System.out.print(M.getByValue(i).name() + ",");
        }
        System.out.println();
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
                changeValue(color, 0, 2);
            } else if (axis == 1) {
                changeValue(color, 0, 1);
            } else if (axis == 2) {
                changeValue(color, 1, 2);
            }
        }

        private void changeValue(int[] originColor, int i, int i1) {
            int tmp = originColor[i];
            originColor[i] = originColor[i1];
            originColor[i1] = tmp;
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
        BLACK(0), YELLOW(1), RED(2), GREEN(3), BLUE(4), ORANGE(5);
        private int value;

        Color(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    enum M {
        U(0), D(1), L(2), R(3), F(4), B(5),
        U1(6), D1(7), L1(8), R1(9), F1(10), B1(11),
        U2(12), D2(13), L2(14), R2(15), F2(16), B2(17);

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
