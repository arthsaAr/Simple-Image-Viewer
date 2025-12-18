# Simple Image Viewer (Java Swing)

## About the Application
This GUI-based Image Viewer allows users to browse, view, and manipulate images.  
The application is built using Java Swing and AWT and supports smooth navigation, brightness adjustment, resizing, and zooming through an intuitive graphical interface.

---

## How to Use the Image Viewer

### Opening Images
1. Select a folder containing multiple images **OR** select a single image using the file navigator.
2. If a folder is selected, a new window will display all images in that folder as thumbnails (small icons).
3. Click on any thumbnail to open the image in the control panel window where different settings can be applied.

---

## Help Option
- Use the **Help** menu to display instructions on how to use the application.

---

## Quit
- Use the **Quit** button or close the application window to exit.

---

## Image Viewer Features
- View the selected image with real-time updates (image size updates dynamically).
- Zoom in and out using the zoom drop-down menu with preset zoom percentages.
- Resize the image by entering custom width and height values, then clicking **Apply**.
- Adjust image brightness using the brightness slider:
  - Drag left to darken the image
  - Drag right to brighten the image
- Image details such as width and height are displayed below the image.

---

## Navigation Inside the Viewer
- **Home**: Returns to the main welcome screen.
- **Images Directory**: Returns to the thumbnail view of the last opened folder.

---

## Application Information
- Click the **"i"** button in the toolbar to view application version and author details.

---

## Notes
- All image modifications are temporary and are applied only while the application is running.

---

## Supported Image Formats
- `.jpg`
- `.jpeg`
- `.png`
- `.gif`
- `.bmp`
- `.wbmp`
- `.webp`

---

Thank you for reading the manual.  
Please contact me to report any bugs in the application. I will work on fixing them in future versions.

---

## Compile
To compile the project, run:
```bash
ant compile
```

---

## Testing (JUnit)
To run tests and generate a testing report:
```bash
ant test
```

Then open the report in a web browser:
```bash
open report/html/index.html
```

---

## Build a Distributable (JAR File)
To build an executable JAR file:
```bash
ant jar
```

---

## Running

### Without Ant (Command Line)
To run the application using the generated JAR file (created at `dist/ImageViewer.jar` by default):
```bash
java -jar dist/ImageViewer.jar
```

---

### With Ant
To run the application using Ant (also builds the application if necessary):
```bash
ant
```

---

## JavaDoc
To generate backend (API-level) documentation:
```bash
ant javadoc
```

Then open:
```bash
open doc/index.html
```

---

## Cleaning
To remove generated build files:
```bash
ant clean
```
