
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.ArrayList;
import java.io.IOException;

public class Main {


    public static Img creerImgLigne(){
		int[][] tab = {{255}, {200},{100}};
		return new Img(tab);
    }

    public static InstanceSegmentation creerInstanceLigne(){
		int[][] tab = {{255}, {200},{100}};
		ArrayList<Couple<Integer,Integer>> f = new ArrayList<>();
		ArrayList<Couple<Integer,Integer>> b = new ArrayList<>();
		f.add(new Couple<>(2,0));
		b.add(new Couple<>(0,0));
		InstanceSegmentation instanceSegmentation = new InstanceSegmentation(new Img(tab),f,b);
		return instanceSegmentation;
	
    }

    public static Graphe creerPetitGraphe(){
	Graphe g = new Graphe(3);
	g.set(0,1,10);
	g.set(1,2,20);
	return g;
    }

    public static ArrayList<Integer> testMinCut(){
		int[][] tab = {{200,180}, {120,10}};

		ArrayList<Couple<Integer,Integer>> f = new ArrayList<>();
		ArrayList<Couple<Integer,Integer>> b = new ArrayList<>();
		f.add(new Couple<>(1,1));
		b.add(new Couple<>(0,0));

		Img img = new Img(tab);
		InstanceSegmentation instanceSegmentation = new InstanceSegmentation(img,f,b);
		InstanceSegmentationGraphe instanceSegmentationGraphe = new InstanceSegmentationGraphe(instanceSegmentation);
		Reseau reseau = new Reseau(instanceSegmentationGraphe);

		reseau.set(0,1,10);
		reseau.set(0,2,100);
		reseau.set(2,3,1);
		reseau.set(1,3,1);
		Couple<Flot, ArrayList<Integer>> res = reseau.flotMaxCoupeMin();

		if (res.getElement1().getVal() == 2){ // test si le flot max est bien égale a 2
			return reseau.coupeMin();
		} else {
			return null;
		}

   }

    public static void addPoints(ArrayList<Couple<Integer,Integer>> liste, int i, int j, int size){
		for(int x=0;x<size;x++){
	    	for(int y=0;y<size;y++){
				liste.add(new Couple<>(i+x, j+y));
	    	}
		}
    }

    public static void main(String args[]) throws FileNotFoundException, IllegalArgumentException, IOException {
		System.out.println("début Main");

		ArrayList<Integer> list = new ArrayList<>();

		list.add(4);
		list.add(0);
		list.add(1);
		list.add(2);

		if (testMinCut().equals(list)) {
			System.out.println("Test ok !");
		}

		// test à dé-commenter tout à la fin du TP, non noté, juste pour tester avec une "vraie" image!


		Img imageFich = new Img("images/baby_2k.pgm");


		ArrayList<Couple<Integer,Integer>> bbB = new ArrayList<>();
		ArrayList<Couple<Integer,Integer>> bbF = new ArrayList<>();
		int c = 3;

		addPoints(bbB,0,0,c);
		addPoints(bbB,7,5,c);
		addPoints(bbB,3,25,c);
		addPoints(bbB,imageFich.nbColonnes()/2,0,c);
		addPoints(bbB,imageFich.nbColonnes()-5,11,c);

		addPoints(bbF,imageFich.nbColonnes()/2,imageFich.nbColonnes()/2-3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2,imageFich.nbColonnes()/2,c);
		addPoints(bbF,imageFich.nbColonnes()/2-10,imageFich.nbColonnes()/2+3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2+5,imageFich.nbColonnes()/2+3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2+5,imageFich.nbColonnes()/2+7*c,c);

		InstanceSegmentation isegFich = new InstanceSegmentation(imageFich,bbF,bbB);
		Img resFich = isegFich.creerImageSegmentee();
		resFich.creerImage("images/outputbaby_2k");
    }


}
