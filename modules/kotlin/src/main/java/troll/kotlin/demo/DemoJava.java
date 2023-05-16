package troll.kotlin.demo;

public class DemoJava {


    void setInterface(Maker maker) {

    }

    public static void main(String[] args) {
        DemoJava demoJava = new DemoJava();
        demoJava.setInterface(new Maker() {
            @Override
            public void test() {

            }
        });
    }
}



