public class balagan {
    static class A{
            public int i;
            public  A() { set(5); }
            public void set(int i) { this.i = i; }
            @Override
            public String toString() {return String.format("%03d",i);}
        static class B extends A{
            public B() {super();}
            @Override
            public void set(int i){this.i = i + 1;}
            public void set2(int i ) {this.i = i +2;}
        }
        public static void main(){
            A a = new A();
            B b = new B();
            System.out.println("a= " + a + "b =" + b);
            b.set2(6);
            System.out.println("a= " + a + "b =" + b);
            a = b;
            System.out.println("a= " + a + "b =" + b);
            a.set(10);
            System.out.println("a= " + a + "b =" + b);
            b = new B();
            System.out.println("a= " + a + "b =" + b);
            a = new B();
            System.out.println("a= " + a + "b =" + b);
            b =  (B) a;
            System.out.println("a= " + a + "b =" + b);
            b.set2(1);
            System.out.println("a= " + a + "b =" + b);
        }

    }
}
//