package XML;


import RPGGame.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by POU on 02-03-2016.
 */
public class UseXML
{

    public void savePlayerToXML(ArrayList<String> playerInfo)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("players");
            doc.appendChild(rootElement);

            Element player = doc.createElement("player");
            rootElement.appendChild(player);

            Element playerName = doc.createElement("playerName");
            playerName.appendChild(doc.createTextNode(playerInfo.get(0)));
            player.appendChild(playerName);

            Element level = doc.createElement("level");
            level.appendChild(doc.createTextNode(playerInfo.get(1)));
            player.appendChild(level);

            Element health = doc.createElement("health");
            health.appendChild(doc.createTextNode(playerInfo.get(2)));
            player.appendChild(health);

            Element dmg = doc.createElement("dmg");
            dmg.appendChild(doc.createTextNode(playerInfo.get(3)));
            player.appendChild(dmg);

            Element experience = doc.createElement("experience");
            experience.appendChild(doc.createTextNode(playerInfo.get(4)));
            player.appendChild(experience);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Player.xml"));

            transformer.transform(source, result);

            System.out.println("File saved!");

        }
        catch (ParserConfigurationException pce)
        {
            pce.printStackTrace();
        }
        catch (TransformerException tfe)
        {
            tfe.printStackTrace();
        }
        readPlayerFromXML();
    }
    public void readPlayerFromXML()
    {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("Player.xml"));

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            System.out.println(root.getNodeName());

            NodeList nList = document.getElementsByTagName("player");
            System.out.println("============================");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                System.out.println("");
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Player Name : " + eElement.getElementsByTagName("playerName").item(0).getTextContent());
                    System.out.println("Level : " + eElement.getElementsByTagName("level").item(0).getTextContent());
                    System.out.println("Damage : " + eElement.getElementsByTagName("dmg").item(0).getTextContent());
                    System.out.println("Experience : " + eElement.getElementsByTagName("experience").item(0).getTextContent());
                }
            }
        }
        catch (Exception e)
        {

        }

    }
}
