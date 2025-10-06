package edu.kingsu.SoftwareEngineering.ImageViewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.nio.file.InvalidPathException;
import javax.swing.*;

/**
 * Main class for Image Viewer applicaiton.
 * <p>
 * This class initializes a GUI application that allows users to:
 * <br>
 * 1. Browse and open image files like PNG, JPG, JPEG etc.
 * <br>
 * 2. Zoom in and out using preset zoom options.
 * <br>
 * 3. Resize images to a custom width and height pixels.
 * <br>
 * 4. Adjust image brightness using the image slider.
 * <br>
 * 5. Also allows to access the help option.
 * <br>
 *
 * @author Raashtra KC
 * @version 1.0
 */

public class Main implements ActionListener{

  // Declaring some private and public variables for our GUI objects(like frame, labels, textfields, menubars, menuItems) etc.
  /**
   * Main frame of the application
   */
  private static JFrame frame;
  /**
   * Width of the application window
   */
  private final static int FRAME_WIDTH = 700;
  /**
   * Height of the application window
   */
  private final static int FRAME_HEIGHT = 700;
  /**
   * Main panel for the home screen
   */
  private static JPanel panel;
  /**
   * Button to browse and open image files
   */
  private static JButton button1;
  /**
   * Button to show help instructions
   */
  private static JButton InfoButton;
  /**
   * Button to quit the application
   */
  private static JButton quitButton;
  /**
   * Button to open a folder containing images
   */
  private static JButton folderOpener;
  /**
   * Starting zoomAmount value setting to 1.0 for 100%
   */
  private double zoomAmount = 1.0;
  /**
   * Icon representating the image
   */
  private static ImageIcon imageIcon;
  /**
   * THe original image loaded from the path selected
   */
  private static Image originalImg;
  /**
   * Backupimage (used in brightness adjustments)
   */
  private static Image backUpimage;
  /**
   * label that stores and shows displays the image
   */
  private JLabel imageLabel;
  /**
   * Information label for the heading
   */
  private JLabel Info1;
  /**
   * Information label for displaying the image size
   */
  private JLabel Info2;
  /**
   * Information label for zoom options
   */
  private JLabel Info3;
  /**
   * Image backup(second) for when brightness and zoom percentages are changed
   */
  private static Image newImageIs;
  /**
   * Button to apply custom image size after entering into specific width and height field
   */
  private static JButton applyButton;
  /**
   * Text field for custom width input
   */
  private JTextField widthField;
  /**
   * Declaring a File variable to store the last opened directory(for easier way to get back to it from other screen)
   */
  private File lastOpened = null;
  /**
   * Text field for custom height input
   */
  private static JTextField heightField;
  /**
   * Menu bar at the top of the frame
   */
  private static JMenuBar menuBar;
  /**
   * FIle menu in the menu bar
   */
  private static JMenu fileMenu;
  /**
   * Menu item to open a new image 
   */
  private static JMenuItem openItem;
  /**
   * Menu item to exit the application
   */
  private static JMenuItem exitItem;
  /**
   * Help menu in the menu bar
   */
  private static JMenu Help;
  /**
   * Help item to display How-to-use instructions
   */
  private static JMenuItem HelpItem;
  /**
   * Features item to display Features of the application
   */
  private static JMenuItem featuresDisplay;
  /**
   * Menu for getting back to home screen
   */
  private static JMenu Home;
  /**
   * Home screen item, which when clicked redirects the applicaiton back to HOme
   */
  private static JMenuItem HomeIcon;
  /**
   * Home screen item, which when clicked redirects the applicaiton back to DIrectory containing all images
   */
  private static JMenuItem getBacktoDir;
  /**
   * Brightened version of the image after slider is increased or decreased
   */
  BufferedImage newBrightImage;
  /**
   * Default window title
   */
  private static final String defaultTitle = "Image Viewer";      //setting a default title for our Image Viewer application as Image Viewer
  /**
   * File chooser dialog to select the image files
   */
  private static JFileChooser fileChooser;
/**
 * Initializing the fixed zoom in and zoom out options
 */
  //initializing some pre-set zoom in and zoom out options for selection by user as needed
  String[] zoomOptions = { "50%", "75%", "100%", "125%", "150%", "200%" };
  /**
   * Setting the supported image file extensions
   */
  String[] accepted = {".jpg", ".jpeg", ".png", ".gif", ".bmp", ".wbmp", ".webp", ".tif", ".svg"};
  /**
   * DropDown menu for selecting zoom levels.
   */
  JComboBox<String> zoomComboBox;
  /**
   * Current image width.
   */
  int width;
  /**
   * Current image height
   */
  int height;


  /**
   * Constructs the initial frame/window of the Image Viewer application
   * Displays the welcome screen with "Select a Folder", "Select a Image", "Help" and "Quit" options.
   */
  public Main(){
    frame = new JFrame(defaultTitle);

    JLabel welcomeLabel = new JLabel("Welcome to Image Viewer");
    welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
    
    folderOpener = new JButton("Select a folder");      //first button of home screen to select a directory with images
    folderOpener.setFont(new Font("Tahoma", Font.BOLD, 12));
    folderOpener.setPreferredSize(new Dimension(150,40));
    folderOpener.setMaximumSize(new Dimension(150,40));
    folderOpener.addActionListener(this);
    folderOpener.setFocusPainted(false);

    button1 = new JButton("Select a Image");            //second button if the user wants to select a specific image instead of a directorty containing images
    button1.setFont(new Font("Tahoma", Font.BOLD, 12));
    button1.setPreferredSize(new Dimension(150,40));
    button1.setMaximumSize(new Dimension(150,40));
    button1.addActionListener(this);
    button1.setFocusPainted(false);

    InfoButton = new JButton("Help");       //3rd button for reading the instructions of the app and how to use it
    InfoButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    InfoButton.setPreferredSize(new Dimension(150,40));
     InfoButton.setMaximumSize(new Dimension(150,40));
    InfoButton.addActionListener(this);
    InfoButton.setFocusPainted(false);
    InfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    quitButton = new JButton("Quit");       //4th button to quit the app
    quitButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    quitButton.setPreferredSize(new Dimension(150,40));
    quitButton.setMaximumSize(new Dimension(150,40));
    quitButton.addActionListener(ev -> System.exit(0));
    quitButton.setFocusPainted(false);
    quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    //Adding all the buttons into the frame with proper center alighnement into the screen
    panel.add(Box.createVerticalStrut(20));       //adding a padding between the top bar and the label placed below
    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(welcomeLabel);
    panel.add(Box.createVerticalStrut(40));

    folderOpener.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(folderOpener);
    panel.add(Box.createVerticalStrut(20));

    button1.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(button1);
    panel.add(Box.createVerticalStrut(20));

    InfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(InfoButton);
    panel.add(Box.createVerticalStrut(20));

    quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(quitButton);
    
    frame.add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle(defaultTitle);
    frame.setLocationRelativeTo(null);

    frame.pack();
    frame.setSize(FRAME_WIDTH-300, FRAME_HEIGHT-300);     //setting the size of our home screen
    frame.setVisible(true);
  }

  /**
  * This is the main method that calls the Image Viewer application
  *
  * @param args Is not used in this specific application
  */
  public static void main(String[] args) {
    new Main();       //calling our default constructor when we want to start the application in fresh
  }

  /**
   * All in one actionEvent handler which Handles all GUI actions triggered by user such as:
   * <br>
   * 1. Browsing and selecting image files
   * <br>
   * 2. Applying custom size and changing the image
   * <br>
   * 3. Opening the Help window/instruction dialogue
   * <br>
   * 4. Zooming in/out of the image.
   * <br>
   * 
   * @param e The ActionEvent triggered by the user  
   */
  @Override
	public void actionPerformed(ActionEvent e) {  
    if(e.getSource() == button1 || e.getSource() == openItem){      //Handling event when Browse a IMage button is clicked from the home screen
        panel.remove(button1);
        frame.setVisible(false);
        frame.getContentPane().removeAll();                         //removing everything from the front home screen and displaying a file chooser
        fileChooser = new JFileChooser(System.getProperty("user.dir"));   //displaying a file chooser in current directory
        fileChooser.setDialogTitle("Image Selector");

        int choosenFile = fileChooser.showOpenDialog(null);

        try {
            if(choosenFile == JFileChooser.APPROVE_OPTION){
              secondFrame();      //calling our secondFrame which initializes and creates a new window for showing/and controlling/modifying the image that was selected
            } else if(choosenFile == JFileChooser.CANCEL_OPTION){
              new Main();       //handling if the user denies to select a file than re-opening the home screen before quiting
            }
        } catch (InvalidPathException ev) {
          System.out.println("File selection failed");      //if none of the options were clicked, or file failed to open throwing a error and closing the application
						System.exit(0);
        }
     } else if(e.getSource() == applyButton){         
        int newW = Integer.parseInt(widthField.getText());
        int newH = Integer.parseInt(heightField.getText());
        if(newW > 0 && newH > 0){
          BufferedImage scaledimage = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2d = scaledimage.createGraphics();

          g2d.drawImage(originalImg, 0, 0, newW, newH, null);
          g2d.dispose();

          newImageIs = scaledimage;
          imageLabel.setIcon(new ImageIcon(newImageIs));

          Info2.setText("Size: "+newW+" * "+newH+" pixels");      //this info section keeps on updating along with different control options when used on the image

          imageLabel.setPreferredSize(new Dimension(newW, newH));
          imageLabel.revalidate();
          imageLabel.repaint();
          backUpimage = newImageIs;
          zoomAmount = 1.0; // reset zoom amount after custom size is applied
        } else {
          JOptionPane.showMessageDialog(null, "Please enter positive integers for width and height.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        widthField.setText("");
        heightField.setText("");

    } else if(e.getSource() == InfoButton || e.getSource() == HelpItem){    //handling event when help or How to use button is clicked
        String infoMessage = "How to use the Image Viewer: \n\n"
                            + "1. Select a Folder:\n"
                            + "   - Choose a folder from your computer and wait for a new window to appear.\n"
                            + "   - All supported images in that folder (.jpg, .jpeg, .png, .gif, .bmp, .wbmp, .webp, .tif, .svg) will appear as thumbnails in the new window.\n"
                            + "   - Click on any image thumbnail to open it in the image viewer with controls in it.\n\n"

                            + "2. Select an Image:\n"   //I have addeda extra feature to directly open the image of user's choice
                            + "   - Choose an individual image file directly from your system(Opens on your current directory by default).\n"
                            + "   - The selected image will open in the image viewer window.\n\n"

                            + "3. Help:\n"
                            + "   - Displays this instruction guide on how to use the application.\n\n"

                            + "4. Quit:\n"
                            + "   - Closes the application(Or use 'X' from the window)\n\n";
        JOptionPane.showMessageDialog(frame, infoMessage, "Help - Image Viewer Instructions", JOptionPane.INFORMATION_MESSAGE);
    } else if(e.getSource() == zoomComboBox){     //handling the zoom when different zoom percentages are clicked
        String selectedZoom = (String) zoomComboBox.getSelectedItem();
        if (selectedZoom != null) {
            selectedZoom = selectedZoom.replace("%", "");
            try {
                int zoomPercent = Integer.parseInt(selectedZoom);
                zoomAmount = zoomPercent / 100.0;       //setting appropriate zoomAmount to later increase or decrease the width/height of image as needed.
                zoomApply();      //calling the zoomAPply function to finally apply corresponding zoom into the image
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid zoom percentage selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else if(e.getSource() == folderOpener){     //handling event when Browse a folder option is clicked by the user
        panel.remove(button1);
        frame.setVisible(false);
        frame.getContentPane().removeAll();   
        JFileChooser folderSelect = new JFileChooser(System.getProperty("user.dir"));     //setting the default directory to user.dir, which can be later changed by the user
        folderSelect.setDialogTitle("Select a Folder");     //setting the title of the directory
        folderSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   //making the default operation to only choose directory but not image

        int re = folderSelect.showOpenDialog(null);
        if(re == JFileChooser.APPROVE_OPTION){
          File selectedDir = folderSelect.getSelectedFile();
          displayFolderSelected(selectedDir);
        } else if(re == JFileChooser.CANCEL_OPTION){
            new Main();       //handling if the user denies to select a file than re-opening the home screen before quiting
        }
    } else if(e.getSource() == featuresDisplay){          //handling event when Features option is clicked
      String infoMessage = "Image Viewer Features:\n"
                            + "• View the selected image with real-time updates.\n"
                            + "• Zoom in/out using the zoom drop-down (50% to 200%).\n"
                            + "• Enter custom width and height and click 'Apply' to resize the image.\n"
                            + "• Adjust image brightness using the brightness slider:\n"
                            + "     - Drag left to darken, right to brighten.\n"
                            + "• View image details like width × height below the image.\n\n"

                            + "Navigation Inside Viewer:\n"
                            + "• Home: Returns to the main welcome screen.\n"
                            + "• Images Directory: Returns to the image thumbnails of the last opened folder.\n\n"

                            + "App Info:\n"
                            + "• Click the 'i' button in the toolbar for app version and author details.\n\n"

                            + "All changes are temporary and limited only within the application.\n\n";
        JOptionPane.showMessageDialog(frame, infoMessage, "Features - Image Viewer Instructions", JOptionPane.INFORMATION_MESSAGE);
    }
    
  }

  /**
   * Displays a list of thumbnails of all supported image files in the selected directory by user.
   * <br>
   * This method shows the small preview of the images in the directory as icons and list them in a layout
   * <br>
   * Clicking a thumbnail opens the respective image and it reuses {@code secondFrame()} method.
   * <br>
   * "Back" button allows the user to return to home screen
   * @param folderS The folder containing image files selected by user to display as thumbnails.
   */
public void displayFolderSelected(File folderS){
    lastOpened = folderS;       //storing the selected folder for getting back to the directory when needed(this enables to use getback functionality from other parts of the application)
    
    //clearing the previous GUI components before restructuring it
    panel.remove(button1);
    frame.setVisible(false);
    frame.getContentPane().removeAll();   

    //setting the window title for the selected the folder
    frame.setTitle("Browse Images - "+folderS.getName());

    JPanel thumbnailsIcon = new JPanel(new FlowLayout(FlowLayout.LEFT));  //creating a panel to hold all the thumbnails of the images available in the current directory
    JScrollPane scrollPane = new JScrollPane(thumbnailsIcon);
    scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT - 100));    //setting the dimension for our new scrollpane

    //storing the current working directory to list the image files
    File currDir = folderS;

    //storing only the image file with the extension of out choice into imageFIles so that we can later display them into out frame/panel
    File[] imageFiles = currDir.listFiles( (dir, name) -> {
      for(String accept : accepted) {
        if(name.toLowerCase().endsWith(accept)) {
          return true;
        }
      }
      return false;
    });

    //if there were no images in the current directory than handling the error and returning back to home screen
    if(imageFiles == null || imageFiles.length == 0){
      JOptionPane.showMessageDialog(frame, "No Images found in current directory");
      new Main();
      return;
    }

    //looping through each image inside the directory to create a small thumbnail which is clickable
    for(File imageFile : imageFiles){
      ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
      //setting the dimension of the thumbnail to 100*100
      Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

      //creating a button(since it has clickable function) with the thumbnail image in it
      JButton thumButton = new JButton(new ImageIcon(img));
      thumButton.setPreferredSize(new Dimension(100, 100));
      thumButton.setToolTipText(imageFile.getName());

      //handling each button clicked event and calling our seconfFrame to provide additional functionalities for modifying the image.
      thumButton.addActionListener(e -> {
        fileChooser = new JFileChooser();     //reusing the image loader
        fileChooser.setSelectedFile(imageFile);
        secondFrame();    //calling our image viewer/editor method
      });

      //adding all the buttons into out panel
      thumbnailsIcon.add(thumButton);
    }

    //creating a button with home icon to get back to home screen
    JButton getBack = new JButton(new ImageIcon("home.png"));
    getBack.addActionListener(e -> {
      thumbnailsIcon.remove(button1);
      frame.setVisible(false);
      frame.getContentPane().removeAll();
      new Main();
    });

    //adding our button at the bottom of the frame/panel
    JPanel bottomPanel = new JPanel();
    bottomPanel.add(getBack);

    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

    //refrasing all the gui elements and redisplaying it in the screen
    frame.revalidate();
    frame.pack();
    frame.setVisible(true);

}

/**
 * Opens a new frame to display the selected image by user and provides different controls
 * for zooming, resizing and adjusting brightness.
 * 
 * It also sets  up the menubar and toolbar components
 */
  public void secondFrame(){      //creating a second window for viewing the image file selected
      frame.setVisible(false);
      frame.getContentPane().removeAll();  
      File selected = fileChooser.getSelectedFile();
              String path = selected.getAbsolutePath().trim();      //storing the path of image as a string
					    if(path.isEmpty() || !selected.exists()){
						      JOptionPane.showMessageDialog(null, "Please choose a Valid Path", "Load Error!", JOptionPane.WARNING_MESSAGE);        //throwing error if the image failed to open
						      System.exit(0);
				  	  }

              menuBar = new JMenuBar();     //creating a menu bar with open and exit as menu items making user easy to open next image from the same window
              fileMenu = new JMenu("File");
              openItem = new JMenuItem("Open");
              openItem.addActionListener(this);
              exitItem = new JMenuItem("Exit");
              exitItem.addActionListener(ev -> System.exit(0));     //directly handlign if the user clicks exit option than closing the program

              Help = new JMenu("Help");     //Creating a new menu item for menu bar for option to read How to use mannual
              HelpItem = new JMenuItem("How to use?");
              HelpItem.addActionListener(this);     //handling the how to use option click seperately and calling usecase instructions

              featuresDisplay = new JMenuItem("Features");
              featuresDisplay.addActionListener(this);

              Home = new JMenu();                   //creating another menuitem where a home icon appears allowing user to easily get back to home screen
              Home.setIcon(new ImageIcon("home.png"));
              HomeIcon = new JMenuItem("Home Screen");
              HomeIcon.addActionListener(evv -> {     //handling event by removing everything in current frame and recalling our COnstructor
                panel.remove(button1);
                frame.setVisible(false);
                frame.getContentPane().removeAll();
                new Main();
              });

              getBacktoDir = new JMenuItem("Images Directory");
              getBacktoDir.addActionListener(e -> {
                if(lastOpened != null){
                  displayFolderSelected(lastOpened);      //getting back to the user selected directory and display the images as thumbnails again
                } else {
                  JOptionPane.showMessageDialog(frame, "<html>No folders were selected prior to opening this image<br>Please use the 'select folder' option for enabling this function</html>");
                }
              });

              //adding all the menuitems to menu bar and setting them as our benubar to the frame
              Help.add(HelpItem);
              Help.add(featuresDisplay);
              Home.add(HomeIcon);
              Home.add(getBacktoDir);

              fileMenu.add(openItem);
              fileMenu.add(exitItem);
              menuBar.add(Home);
              menuBar.add(fileMenu);
              menuBar.add(Help);


              imageIcon = new ImageIcon(path);      //creating a new imageicon from the path selected by file chooser
              originalImg = imageIcon.getImage();     //getting the image from the path and storing into originalImg
              backUpimage = originalImg;              //backingup the original image twice
              newImageIs = originalImg;
              imageLabel = new JLabel(new ImageIcon(originalImg));    //adding the image into the frame as a JLabel
              imageLabel.setBorder(null);
              imageLabel.setOpaque(false);
              
              frame = new JFrame(defaultTitle);
              frame.setJMenuBar(menuBar);

              //creating zoomCOmboBox with different options to zoom the image 
              zoomComboBox = new JComboBox<>(zoomOptions);
              zoomComboBox.setSelectedItem("100%");     //selecting 100% as a default zoom of the image openeed
              zoomComboBox.addActionListener(this);     //handling the zoom when different zoom options are selected

              JPanel zoomPanelIs = new JPanel(new FlowLayout(FlowLayout.LEFT));
              Info3 = new JLabel("Zoom: ");     //initializing zoom label with dropdown for selecting the appropriate zoom options as needed
              zoomPanelIs.add(Info3);
              zoomPanelIs.add(zoomComboBox);
              zoomPanelIs.setAlignmentX(Component.LEFT_ALIGNMENT);

              JToolBar toolBar = new JToolBar();
              toolBar.setFloatable(false);

              JButton openBtn = new JButton();
              openBtn.addActionListener( ev -> {
                String aboutMessage = "Image Viewer Application\n"        //setting the version and writer instructions for about me section
                      + "Version 1.0\n"
                      + "By Raashtra KC\n"
                      + "This application allows users to view and change image appearances with features like zooming, resizing, and brightness adjustment.";
                JOptionPane.showMessageDialog(frame, aboutMessage, "About Image Viewer", JOptionPane.INFORMATION_MESSAGE);
              });
              openBtn.setIcon(new ImageIcon("info.png"));     //making a info button with i logo which is clickable
              toolBar.add(openBtn);

              width = imageIcon.getIconWidth();     //storing the main image width and height for later modifications
              height = imageIcon.getIconHeight();

              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.setTitle(defaultTitle);     //setting the default title of our image control panel.
              frame.setLocation(100, 100);

              frame.pack();
              frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
              frame.setLocationRelativeTo(null);
              
              JPanel imagePanel = new JPanel(new BorderLayout());     //using borderlayout to correctly place the image display on top of the frame and all the control elements at the bottom of the frame
              imagePanel.add(imageLabel, BorderLayout.CENTER);

              JPanel controlsPanel = new JPanel();
              controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

              Info1 = new JLabel(" Info:");       //main heading of the image control panel label
              
              Info2 = new JLabel("Size: "+width+" * "+height+" pixels");      //shows the live size of the image(even when it is changed it keeps on updating)
              Info1.setAlignmentX(Component.LEFT_ALIGNMENT);
              Info2.setAlignmentX(Component.LEFT_ALIGNMENT);

              Info1.setFont(new Font("Segoe UI", Font.BOLD, 18));     //setting the font of the main heading to seogoe


              JLabel customLabel = new JLabel("Custom Size: ");     //label for entering custom size by the user and apply it using a button
              widthField = new JTextField(5);
              heightField = new JTextField(5);
              applyButton = new JButton("Apply");     //apply button along with handling when it is clicked in the event handler we setup a above
              applyButton.setFocusPainted(false);

              JPanel customSizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
              customSizePanel.add(customLabel);
              customSizePanel.add(new JLabel("Width:"));    //creating fields for width and height entering by the user
              customSizePanel.add(widthField);
              customSizePanel.add(new JLabel("Height:"));
              customSizePanel.add(heightField);
              customSizePanel.add(applyButton);
              applyButton.addActionListener(this);
              customSizePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

              JSlider brightnessSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
              brightnessSlider.setMajorTickSpacing(50);     //setting extra spacing for proper alignment of components on screen
              brightnessSlider.setMinorTickSpacing(10);
              brightnessSlider.setPaintTicks(true);
              brightnessSlider.setPaintLabels(true);
              brightnessSlider.addChangeListener(event -> {   //handling the brightness slider, and controlling the brightness of image when the slider is moved
                BufferedImage originalBufferedImage;
                  originalBufferedImage = toBufferedImage(backUpimage);     //initializing the image with converted buffered image from the method
                
                  int changeValue = brightnessSlider.getValue();
                  float imageScale = 1.0f;
                  float offset = changeValue;

                  RescaleOp op = new RescaleOp(imageScale, offset, null);
                  newBrightImage = op.filter(originalBufferedImage, null);
                  newImageIs = newBrightImage;      //this makes sure the zoom and brightness are correctly synced up 
                  imageLabel.setIcon(new ImageIcon(newBrightImage));    //displaying the new image with changed brightness into the screen/ and refreshing the panel
                  controlsPanel.revalidate();
                  controlsPanel.repaint();

              });

              JPanel brightnessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
              brightnessPanel.add(new JLabel("Brightness:"));
              brightnessPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
              brightnessPanel.add(brightnessSlider);

              controlsPanel.add(Info1);
              controlsPanel.add(Info2);
              controlsPanel.add(zoomPanelIs);
              controlsPanel.add(customSizePanel);
              controlsPanel.add(brightnessPanel);

              JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, imagePanel, controlsPanel);
              splitPane.setResizeWeight(0.70);
              splitPane.setDividerSize(0);
              splitPane.setEnabled(false); //not allow for moving the divider
              frame.add(toolBar, BorderLayout.SOUTH);       //setting the place for toolBar to SOUTH(which is the lower part of the frame)
              frame.add(splitPane, BorderLayout.CENTER);
              frame.pack();
              frame.setResizable(false);
              frame.setVisible(true);         
  }

  /**
   * Applies the zoomlevel selected by the user from the drop-down menu to the image that is currently opened.
   * The image is scaled smoothly to preserve the quality as well
   */
  public void zoomApply(){   
    int newWidth = (int) (newImageIs.getWidth(null) * zoomAmount);      //increasing or decreasing the width of the image based on the user selected value
    int newHeight = (int) (newImageIs.getHeight(null) * zoomAmount);    
    Info2.setText("Size: "+newWidth+" * "+newHeight+" pixels");   //setting the current size of the image on the screen to the one changed after zooming in or out
    Image newScaledImage = newImageIs.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    backUpimage = newScaledImage;
    imageLabel.setIcon(new ImageIcon(newScaledImage));
    imageLabel.revalidate();      //revalidating everyuthing on the label to update into the fresh new size for the imageLabel containing the imageicon
  }

/**
 * Converts the given {@link Image} to a {@link BufferedImage}
 * <br>
 * For more details <a href="https://stackoverflow.com/questions/13605248/java-converting-image-to-bufferedimage">Click here</a>
 * 
 * @param img The image to convert
 * @return A BufferedImage representation of a Image item
 */
public static BufferedImage toBufferedImage(Image img) {
      if (img instanceof BufferedImage) {
          return (BufferedImage) img;
      }

      BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

      Graphics2D bGr = bimage.createGraphics();
      bGr.drawImage(img, 0, 0, null);
      bGr.dispose();

      return bimage;

    }
}