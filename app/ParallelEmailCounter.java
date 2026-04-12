
class ParallelEmailCounterTest {
    
    private TestCustomCollection<User> collection;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() {
        collection = new TestCustomCollection<>();
        System.setOut(new PrintStream(outContent));
    }
    
    private String getOutput() {
        return outContent.toString().trim();
    }
    
    @Test
    void testEmptyCollection() {
        ParallelEmailCounter.count(collection, "test@test.com");
        assertEquals("0", getOutput());
    }
    
    @Test
    void testCountMatchingEmails() {
        // Создаем пару юзеров через Builder
        User alice1 = new User.Builder()
                .setName("Alice")
                .setPassword("pass123")
                .setEmail("alice@test.com")
                .create();
        
        User bob = new User.Builder()
                .setName("Bob")
                .setPassword("pass456")
                .setEmail("bob@test.com")
                .create();
        
        User alice2 = new User.Builder()
                .setName("Alice2")
                .setPassword("pass789")
                .setEmail("alice@test.com")
                .create();
        
        collection.add(alice1);
        collection.add(bob);
        collection.add(alice2);
        
        ParallelEmailCounter.count(collection, "alice@test.com");
        assertEquals("2", getOutput());
    }
    
    @Test
    void testNoMatchingEmails() {
        User alice = new User.Builder()
                .setName("Alice")
                .setPassword("pass123")
                .setEmail("alice@test.com")
                .create();
        
        User bob = new User.Builder()
                .setName("Bob")
                .setPassword("pass456")
                .setEmail("bob@test.com")
                .create();
        
        collection.add(alice);
        collection.add(bob);
        
        ParallelEmailCounter.count(collection, "charlie@test.com");
        assertEquals("0", getOutput());
    }
    
    @Test
    void testNullTarget() {
        User alice = new User.Builder()
                .setName("Alice")
                .setPassword("pass123")
                .setEmail("alice@test.com")
                .create();
        
        collection.add(alice);
        
        ParallelEmailCounter.count(collection, null);
        assertEquals("0", getOutput());
    }
    
    @Test
    void testNullUserEmail() {
        User userWithNullEmail = new User.Builder()
                .setName("NoEmail")
                .setPassword("pass123")
                .setEmail(null)
                .create();
        
        User alice = new User.Builder()
                .setName("Alice")
                .setPassword("pass123")
                .setEmail("alice@test.com")
                .create();
        
        collection.add(userWithNullEmail);
        collection.add(alice);
        
        ParallelEmailCounter.count(collection, "alice@test.com");
        assertEquals("1", getOutput());
    }
}