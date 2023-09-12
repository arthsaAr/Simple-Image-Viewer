# ImageViewer
The objective of this laboratory is to give you experience developing a GUI using Java and the Swing widget toolkit.  You will become familiar with software repositories (git), ANT, and JavaDoc to provide revision control, build management, and code documentation respectively.  Your task will be open-ended, and you will be required to think creatively about how you will develop a user-friendly GUI.

## Compile
To compile the project run:

```
ant compile
```

## Build a Distributable (JAR file)
To build an executable jar file, run:

```
ant jar
```

## Running

### Without Ant, Command line
To run the application via the portable jar file (this is by default created at `dist/ImageViewer.jar` after building) run:

```
java -jar <jar file>
```

Example:

```
java -jar dist/ImageViewer.jar # This is the default jar location after build
```
### With Ant
To run the application through `ant` (also performs application build if necessary), navigate
to the root of this repository and run:

```
ant
```

## JavaDoc
To create the backend (API-level) documentation run:

```
ant javadoc
```

Then navigate to `doc/index.html` with a web browser. 

```
open doc/index.html
```

## Cleaning
To clean created files from building, navigate to the root of this repository and run:

```
ant clean
```
