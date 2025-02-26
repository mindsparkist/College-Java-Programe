(Due to technical issues, the search service is temporarily unavailable.)

The **`Consumer<T>`** interface in Java is a functional interface introduced in Java 8 as part of the `java.util.function` package. It is designed to represent an operation that **accepts a single input argument and returns no result**. It is ideal for scenarios where you need to perform actions on data without producing an output, such as modifying objects, printing values, or triggering side effects.

---

### **Key Features**:
1. **Abstract Method**: `void accept(T t)`  
   - Performs an operation on the given input argument.  
   - Example: Printing a value, saving to a database, or updating an object’s state.  

2. **Default Method**: `andThen(Consumer<? super T> after)`  
   - Chains multiple `Consumer` operations sequentially.  
   - Example: First print a value, then log it.  

3. **`@FunctionalInterface` Annotation**:  
   - Ensures the interface has exactly one abstract method (enabling lambda/method reference usage).

---

### **Syntax**:
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
    
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
```

---

### **Common Use Cases**:
1. **Iterating Collections** (e.g., with `forEach`):  
   ```java
   List<String> names = List.of("Alice", "Bob", "Charlie");
   names.forEach(name -> System.out.println(name)); // Lambda
   names.forEach(System.out::println);              // Method reference
   ```

2. **Modifying Objects**:  
   ```java
   Consumer<StringBuilder> addExclamation = sb -> sb.append("!");
   StringBuilder sb = new StringBuilder("Hello");
   addExclamation.accept(sb); // sb becomes "Hello!"
   ```

3. **Chaining Operations** (using `andThen`):  
   ```java
   Consumer<String> print = System.out::println;
   Consumer<String> log = s -> Logger.log(s);
   
   Consumer<String> printAndLog = print.andThen(log);
   printAndLog.accept("Action performed"); // Prints + logs
   ```

4. **Passing Behavior to Methods**:  
   ```java
   public void processData(List<Integer> data, Consumer<Integer> processor) {
       data.forEach(processor);
   }
   
   // Usage:
   processData(numbers, n -> System.out.println(n * 2)); // Double & print
   ```

---

### **Specialized Variants**:
For primitive types (to avoid autoboxing overhead):  
- `IntConsumer`: Accepts an `int` argument.  
- `LongConsumer`: Accepts a `long` argument.  
- `DoubleConsumer`: Accepts a `double` argument.  

Example:  
```java
IntConsumer printSquare = n -> System.out.println(n * n);
printSquare.accept(5); // Output: 25
```

---

### **Comparison with Similar Interfaces**:
| **Interface**     | **Purpose**                                      | **Method**              |
|--------------------|--------------------------------------------------|-------------------------|
| `Consumer<T>`      | Accepts input, no return (`void`)                | `void accept(T t)`      |
| `Function<T, R>`   | Accepts input, returns output                   | `R apply(T t)`          |
| `Predicate<T>`     | Accepts input, returns `boolean`                | `boolean test(T t)`     |
| `Supplier<T>`      | No input, returns output                        | `T get()`               |

---

### **Example Breakdown**:
```java
// Define a Consumer to print and uppercase a String
Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());

// Use it:
printUpperCase.accept("hello"); // Output: "HELLO"

// Chain with another Consumer:
Consumer<String> addPrefix = s -> System.out.println("Prefix: " + s);
printUpperCase.andThen(addPrefix).accept("world"); 

// Output:
// "WORLD"
// "Prefix: world"
```

---

### **Best Practices**:
1. **Use Method References** for readability (e.g., `System.out::println`).  
2. **Avoid Side Effects**: Ensure Consumers don’t inadvertently modify shared state.  
3. **Combine with Streams**: Leverage `forEach`, `peek`, or custom operations in stream pipelines.  

---

### **Summary**:
The `Consumer<T>` interface is a versatile tool for encapsulating void operations on data. It promotes clean, functional-style code and is widely used in Java’s Streams API, event handling, and APIs requiring side-effect operations.
