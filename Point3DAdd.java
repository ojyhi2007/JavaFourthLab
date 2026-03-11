public class Point3DAdd {

    public static void addRandomPoint3D(Box<? super Point3D> box) {

        Point3D p = new Point3D(
                Math.random() * 10,
                Math.random() * 10,
                Math.random() * 10
        );

        box.put(p);
    }
}
