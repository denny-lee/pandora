package study.lab.multitheads.prodconsum;

public class Main {

    public static void main(String[] args) {
        Table t = new Table(3);
        new MakerThread("Maker-1", t, 31909).start();
        new MakerThread("Maker-2", t, 66767).start();
        new MakerThread("Maker-3", t, 49797).start();
        new EaterThread("Eater-1", t, 43453).start();
        new EaterThread("Eater-2", t, 21223).start();
        new EaterThread("Eater-3", t, 53323).start();
    }
}
