# MECE
Microsoft Edge Cookie Extractor

# Command line usage
```
java -jar run.jar *{DIRPATH}*
```

# Java usage examples (generate a .csv file with every cookies)
```Java
	ArrayList<Cookie> cookies = MECE.extract();
	System.out.println("name;value;referer");
	for (Cookie cookie : cookies) {
		System.out.println(cookie.name + ";" + cookie.value + ";" + cookie.referer);
	}
```
