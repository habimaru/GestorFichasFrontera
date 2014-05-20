package habimaru.gestorfichasrol.app;

import android.os.Environment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Habimaru on 29/04/2014.
 */
public class Jugador {
    String nombre="";
    String jugador="";
    String mision="";
    String rama="";
    String raza="";
    String sexo="";
    String vit="";
    String exp="";
    String dinero="";

    String historia="";
    String armas="";
    String hechizos="";
    String habilidades="";
    String inventario="";

    /**
     * Constructor vacío. Lo implemento para no recibir nullpointerexception en la creacion de la actividad
     */
    public Jugador(){
    }

    /**
     * Serializa a xml el jugador
     */
    void toXML(){
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // root elements
        Document doc = docBuilder.newDocument();
        Element jugador = doc.createElement("Jugador");
        doc.appendChild(jugador);

        // set attribute to staff element
        jugador.setAttribute("nombre", this.nombre);
        jugador.setAttribute("jugador", this.jugador);
        jugador.setAttribute("mision", this.mision);
        jugador.setAttribute("rama", this.rama);
        jugador.setAttribute("raza", this.raza);
        jugador.setAttribute("sexo", this.sexo);
        jugador.setAttribute("vit", this.vit);
        jugador.setAttribute("exp", this.exp);
        jugador.setAttribute("dinero", this.dinero);
        jugador.setAttribute("historia", this.historia);
        jugador.setAttribute("armas", this.armas);
        jugador.setAttribute("habilidades", this.habilidades);
        jugador.setAttribute("hechizos", this.hechizos);
        jugador.setAttribute("inventario", this.inventario);







        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {

            File sdcard = Environment.getExternalStorageDirectory();
            File directorio = new File(sdcard.getAbsolutePath()+"/rpg/frontera/");

            if(directorio.exists()==false){
            directorio.mkdirs();}

            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(sdcard.getAbsolutePath()+"/rpg/frontera/"+this.nombre+".xml"));

            transformer.transform(source, result);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * Constructor de jugador que lee del fichero xml
     * @param nombre
     */
    public Jugador(String nombre){
        File sdcard = Environment.getExternalStorageDirectory();
        File fXmlFile = new File(nombre);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            Element player= (Element) doc.getElementsByTagName("Jugador").item(0);
            this.nombre=player.getAttribute("nombre");
            this.jugador=player.getAttribute("jugador");
            this.sexo=player.getAttribute("sexo");
            this.raza=player.getAttribute("raza");
            this.rama=player.getAttribute("rama");
            this.vit=player.getAttribute("vit");
            this.exp=player.getAttribute("exp");
            this.dinero=player.getAttribute("dinero");
            this.nombre=player.getAttribute("historia");
            this.armas=player.getAttribute("armas");
            this.hechizos=player.getAttribute("hechizos");
            this.inventario=player.getAttribute("inventario");
            this.habilidades=player.getAttribute("habilidades");


        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}
