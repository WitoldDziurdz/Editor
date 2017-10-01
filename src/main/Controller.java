package main;

import javax.swing.*;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

/**
 * Created by vitia on 20.09.2017.
 */
public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;


    public Controller(View view) {
        this.view = view;

    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    public void resetDocument(){
        if(document!=null) {
            document.removeUndoableEditListener(view.getUndoListener());
            document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
            document.addUndoableEditListener(view.getUndoListener());
            view.update();
        }
    }

    public void setPlainText(String text){
        resetDocument();
        StringReader stringReader = new StringReader(text);
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.read(stringReader,document,document.getLength());
        } catch (Exception e) {
           ExceptionHandler.log(e);
        }
    }

    public String getPlainText(){
        StringWriter writer = new StringWriter();
        HTMLEditorKit editorKit = new HTMLEditorKit();
        try {
            editorKit.write(writer,document,0,document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return writer.toString(); 
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML editor");
        currentFile = null; 
        view.resetUndo();
    }

    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int isFile = jFileChooser.showOpenDialog(view);
        if(isFile == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.read(fileReader,document,0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument(){
        view.selectHtmlTab();
        if(currentFile!=null){
            try {
                new HTMLEditorKit().write(new FileWriter(currentFile),document,0,document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        else {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int isFile = jFileChooser.showSaveDialog(view);
        if(isFile == JFileChooser.APPROVE_OPTION){
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try {
                FileWriter file = new FileWriter(currentFile);
                HTMLEditorKit editorKit = new HTMLEditorKit();
                editorKit.write(file,document,0,document.getLength());

            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }

    }

}
