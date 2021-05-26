public static void assertEquals(Object a, Object b)
{
	if (!a.equals(b)) throw new RuntimeException(a + " != " + b); 
}