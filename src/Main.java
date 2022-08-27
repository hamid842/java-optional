import java.util.Optional;

/**
 * Optional is a container object used to contain not-null objects. Optional object is used to represent null with absent value. This class has various utility methods to facilitate code to handle values as ‘available’ or ‘not available’ instead of checking null values.
 */

@SuppressWarnings({"ConstantConditions", "SameParameterValue"})
public class Main {
    public static void main(String[] args) {
    // ======================== With long time Java ========================
        Cat myCat = findByName("Fred");
        System.out.println(myCat.getAge());

    // What if there is no cat called "Fred"
        if (myCat != null) {
            System.out.println(myCat.getAge()); // get() returns the original Cat object
        } else {
            System.out.println(0);
        }

    // ========================= With Optional ==============================
        Optional<Cat> optionalCat = findByNameWithOptional("Fred");
        System.out.println("Optional: "+optionalCat.get().getAge());

    // but it might be null again
        if (optionalCat.isPresent()) { //isPresent return boolean
            System.out.println("Optional: "+optionalCat.get().getAge());
        } else {
            System.out.println(0);
        }

    // Question: This is like the block code of java, then why Optional?
    // Optional have nicer methods to work with them:
        Cat orElseCat = optionalCat.orElse(new Cat("UNKNOWN",0)); // return a default value
        System.out.println("orElse: "+orElseCat);

        Cat orElseGetCat = optionalCat.orElseGet(()->new Cat("UNKNOWN",0)); //passed as an argument is only executed when an Optional value isn't present.
        System.out.println("orElse: "+orElseGetCat);

        Cat orElseThrowCat = optionalCat.orElseThrow(); // this is exactly like ".get()".
        System.out.println("orElse: "+orElseThrowCat.getAge());

        int mappingCat = optionalCat.map(Cat::getAge).orElse(0);
        System.out.println("WithMap: " +mappingCat);
    }



    // Suppose this is come from database
    private static Cat findByName(String name) {
        return new Cat(name, 3);
    }

    //    Optional come in
    //    Optional is a box it either contains a cat or does not(empty)
    private static Optional<Cat> findByNameWithOptional(String name) {
        Cat optionalCat = new Cat("Fred", 3);
        return Optional.ofNullable(optionalCat); // if you sure it exist use ".of()" or ".empty()" to explicitly return empty.

    }
}