public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 6, 7};
        var search = new jumpSearch();
        var index = search.jSearch(numbers,1);
        System.out.println(index);
    }
}
