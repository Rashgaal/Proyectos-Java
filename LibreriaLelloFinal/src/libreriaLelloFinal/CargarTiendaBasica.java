package libreriaLelloFinal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class CargarTiendaBasica { // Aquí podemos agregar los elementos iniciales para cargar la tienda del tirón

	/* GENERAR LISTAS OFICIALES PARA USAR */

	public static ArrayList<Libro> generarLibrosIniciales() {
		ArrayList<Libro> libros = new ArrayList<>();
		// ESPAÑOL
		libros.add(new Libro("Cien años de soledad", "Gabriel García Márquez", "9780307474728", "Edición 1",
				"Editorial Sudamericana", 15.99, "Realismo mágico", "Español", 100));
		libros.add(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "9788424117974", "Edición 1",
				"Editorial Espasa Calpe", 20.50, "Novela picaresca", "Español", 150));
		libros.add(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "9780307474728", "Edición 1",
				"Editorial Planeta", 12.90, "Misterio", "Español", 80));
		libros.add(new Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", "9780307474728",
				"Edición 1", "Editorial Sudamericana", 18.50, "Romance", "Español", 120));
		libros.add(new Libro("Rayuela", "Julio Cortázar", "9788432226399", "Edición 1", "Editorial Alfaguara", 14.25,
				"Narrativa experimental", "Español", 90));
		libros.add(new Libro("Crónica de una muerte anunciada", "Gabriel García Márquez", "9780307474728", "Edición 1",
				"Editorial Sudamericana", 13.75, "Novela corta", "Español", 110));
		libros.add(new Libro("La casa de los espíritus", "Isabel Allende", "9780307474728", "Edición 1",
				"Editorial Plaza & Janés", 17.80, "Realismo mágico", "Español", 70));
		libros.add(new Libro("Tú también puedes: Cómo conseguí perder 60 kilos y ganar salud", "Carlota Corredera",
				"9780307474958", "Edición 1", "Editorial Seix Barral", 17.95, "Crecimiento personal", "Español", 85));
		libros.add(new Libro("La ciudad y los perros", "Mario Vargas Llosa", "9780307474728", "Edición 1",
				"Editorial Seix Barral", 16.35, "Novela policial", "Español", 85));
		libros.add(new Libro("Los renglones torcidos de Dios", "Torcuato Luca de Tena", "9788466625265", "Edición 1",
				"Editorial Ediciones B", 11.10, "Thriller psicológico", "Español", 95));
		libros.add(new Libro("El túnel", "Ernesto Sabato", "9788432212170", "Edición 1", "Editorial Seix Barral", 13.20,
				"Novela existencialista", "Español", 105));
		// INGLÉS
		libros.add(new Libro("Cosmos", "Carl Sagan", "978-0345539434", "Revised", "Ballantine Books", 15.99, "Ciencia",
				"Inglés", 50));
		libros.add(new Libro("To Kill a Mockingbird", "Harper Lee", "9780061120084", "1st Edition",
				"Harper Perennial Modern Classics", 12.99, "Novel", "English", 100));
		libros.add(new Libro("1984", "George Orwell", "9780451524935", "Centennial Edition", "Signet Classic", 9.99,
				"Dystopian Fiction", "English", 150));
		libros.add(new Libro("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Reprint Edition", "Scribner",
				10.50, "Tragedy", "English", 80));
		libros.add(new Libro("Animal Farm", "George Orwell", "9780451526342", "60th Anniversary Edition",
				"Signet Classic", 8.99, "Political Satire", "English", 120));
		libros.add(new Libro("Brave New World", "Aldous Huxley", "9780060850524", "Reprint Edition",
				"Harper Perennial Modern Classics", 11.99, "Science Fiction", "English", 90));
		libros.add(new Libro("Lord of the Flies", "William Golding", "9780571084838", "Reprint Edition",
				"Faber & Faber", 13.25, "Allegorical Novel", "English", 110));
		libros.add(new Libro("The Catcher in the Rye", "J.D. Salinger", "9780316769488", "Reprint Edition",
				"Back Bay Books", 8.99, "Coming-of-Age Fiction", "English", 70));
		libros.add(new Libro("Of Mice and Men", "John Steinbeck", "9780140186420", "Reprint Edition", "Penguin Books",
				9.50, "Tragicomedy", "English", 85));
		libros.add(new Libro("The Lord of the Rings", "J.R.R. Tolkien", "9780544003415", "Box Edition", "Mariner Books",
				25.99, "High Fantasy", "English", 95));
		libros.add(new Libro("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "9780590353427",
				"Reprint Edition", "Scholastic", 12.99, "Fantasy", "English", 105));
		// JAPONÉS
		libros.add(new Libro("ノルウェイの森", "村上春樹", "9784103534226", "新版", "新潮社", 1500, "小説", "日本語", 100));
		libros.add(new Libro("1Q84", "村上春樹", "9784103534226", "新版", "新潮社", 1800, "ファンタジー", "日本語", 120));
		libros.add(new Libro("永遠のゼロ", "百田尚樹", "9784103534226", "新版", "新潮社", 1600, "戦争小説", "日本語", 80));
		libros.add(new Libro("ハルク：ビギニングス", "原田マハ", "9784103534226", "新版", "新潮社", 1400, "ミステリー", "日本語", 110));
		libros.add(new Libro("進撃の巨人", "諫山創", "9784063845116", "新版", "講談社", 2200, "漫画", "日本語", 90));
		libros.add(new Libro("坂の途中で", "森見登美彦", "9784103534226", "新版", "新潮社", 1700, "青春小説", "日本語", 130));
		libros.add(new Libro("白夜行", "東野圭吾", "9784062951030", "新版", "文藝春秋", 2000, "サスペンス", "日本語", 70));
		libros.add(new Libro("海辺のカフカ", "村上春樹", "9784103534226", "新版", "新潮社", 1600, "小説", "日本語", 95));
		libros.add(new Libro("コンビニ人間", "村田沙耶香", "9784103534226", "新版", "新潮社", 1800, "現代小説", "日本語", 85));
		libros.add(new Libro("グループ・ゼロ", "水上悟志", "9784103534226", "新版", "新潮社", 1500, "サスペンス", "日本語", 105));
		// NORUEGO
		libros.add(new Libro("Naiv. Super.", "Erlend Loe", "9788205305101", "Første utgave", "Tiden Norsk Forlag", 149,
				"Roman", "Norwegian", 100));
		libros.add(new Libro("Havmannen", "Lars Saabye Christensen", "9788252558613", "Første utgave",
				"Gyldendal Norsk Forlag", 179, "Roman", "Norwegian", 120));
		libros.add(new Libro("Sofies verden", "Jostein Gaarder", "9788204133462", "Første utgave", "Aschehoug", 199,
				"Filosofisk roman", "Norwegian", 80));
		libros.add(new Libro("Jeg er en liten kanin", "Ingrid Ch. Hvass", "9788202254831", "Første utgave",
				"Cappelen Damm", 99, "Barnebok", "Norwegian", 110));
		libros.add(new Libro("Fugletribunalet", "Agnes Ravatn", "9788205502803", "Første utgave", "Aschehoug", 169,
				"Roman", "Norwegian", 90));
		libros.add(new Libro("Folk med angst", "Fredrik Hagen", "9788293667958", "Første utgave", "Res Publica", 159,
				"Dystopisk roman", "Norwegian", 130));
		libros.add(new Libro("En Folkefiende", "Henrik Ibsen", "9788252567141", "Første utgave",
				"Gyldendal Norsk Forlag", 119, "Drama", "Norwegian", 70));
		libros.add(new Libro("Victoria", "Knut Hamsun", "9788205242972", "Første utgave", "Forlaget Oktober", 189,
				"Roman", "Norwegian", 95));
		libros.add(new Libro("Berlinerpoplene", "Anne B. Ragde", "9788205369462", "Første utgave", "Tiden Norsk Forlag",
				159, "Roman", "Norwegian", 85));
		libros.add(new Libro("Sult", "Knut Hamsun", "9788205242972", "Første utgave", "Forlaget Oktober", 129, "Roman",
				"Norwegian", 105));
		// ITALIANO
		libros.add(new Libro("La ragazza di Bube", "Carlo Cassola", "9788807881515", "Prima edizione", "Feltrinelli",
				15.99, "Romanzo", "Italiano", 100));
		libros.add(new Libro("Il nome della rosa", "Umberto Eco", "9788845271825", "Edizione speciale", "Bompiani",
				19.99, "Romanzo giallo", "Italiano", 120));
		libros.add(new Libro("Se una notte d'inverno un viaggiatore", "Italo Calvino", "9788806144871",
				"Prima edizione", "Einaudi", 14.50, "Romanzo sperimentale", "Italiano", 80));
		libros.add(new Libro("L'isola del giorno prima", "Umberto Eco", "9788845254095", "Prima edizione", "Bompiani",
				18.50, "Romanzo storico", "Italiano", 110));
		libros.add(new Libro("Gomorra", "Roberto Saviano", "9788806143393", "Edizione economica", "Mondadori", 12.99,
				"Saggio", "Italiano", 90));
		libros.add(new Libro("Io non ho paura", "Niccolò Ammaniti", "9788806222622", "Edizione tascabile", "Einaudi",
				10.99, "Romanzo", "Italiano", 130));
		libros.add(new Libro("La solitudine dei numeri primi", "Paolo Giordano", "9788806183671", "Edizione limitata",
				"Mondadori", 16.50, "Romanzo", "Italiano", 70));
		libros.add(new Libro("L'amica geniale", "Elena Ferrante", "9788866324300", "Prima edizione", "Edizioni e/o",
				17.50, "Romanzo", "Italiano", 95));
		libros.add(new Libro("Il cavaliere inesistente", "Italo Calvino", "9788806184029", "Prima edizione",
				"Mondadori", 14.99, "Romanzo cavalleresco", "Italiano", 85));
		libros.add(new Libro("Sapiens. Da animali a dèi", "Yuval Noah Harari", "9788804672310", "Prima edizione",
				"Mondadori", 22.99, "Saggio", "Italiano", 105));
		
		for (Libro l : libros) {
			l.setStockLibro(100);
		}
		
		
		return libros;
	}

	public static ArrayList<Merchandising> generarMerchaInicial() {
		ArrayList<Merchandising> mercha = new ArrayList<>();
		mercha.add(new Merchandising("Póster del Sistema Solar", "Póster detallado del Sistema Solar", "Astronomía",
				9.99));
		mercha.add(new Merchandising("Camiseta del Universo", "Camiseta con diseño cósmico", "Moda", 19.99));
		mercha.add(new Merchandising("Llavero de la NASA", "Llavero con el logo de la NASA", "Espacio", 5.50));
		mercha.add(new Merchandising("Taza galáctica", "Taza con diseño de galaxias", "Galaxias", 7.99));
		mercha.add(new Merchandising("Puzzle del Universo", "Puzzle con imagen del cosmos", "Entretenimiento", 12.50));
		mercha.add(new Merchandising("Puzzle de paisaje", "Puzzle de montaña", "Naturaleza", 20.99));
		mercha.add(new Merchandising("Puzzle de animales", "Puzzle de selva", "Animales", 15.50));
		mercha.add(new Merchandising("Puzzle de ciudades", "Puzzle de skyline", "Ciudades", 18.75));
		mercha.add(new Merchandising("Puzzle de obras de arte", "Puzzle de cuadros famosos", "Arte", 22.99));
		mercha.add(new Merchandising("Puzzle de personajes", "Puzzle de superhéroes", "Personajes", 25.99));
		mercha.add(new Merchandising("Peluche de osito", "Osito amoroso", "Animales", 12.99));
		mercha.add(new Merchandising("Peluche de gatito", "Gatito mimoso", "Animales", 10.50));
		mercha.add(new Merchandising("Peluche de perrito", "Perrito feliz", "Animales", 15.75));
		mercha.add(new Merchandising("Peluche de unicornio", "Unicornio mágico", "Fantasía", 18.99));
		mercha.add(new Merchandising("Peluche de panda", "Panda sonriente", "Animales", 20.99));
		mercha.add(new Merchandising("Taza de diseño floral", "Taza primaveral", "Floral", 8.99));
		mercha.add(new Merchandising("Taza de gatos", "Taza de gatitos juguetones", "Animales", 9.50));
		mercha.add(new Merchandising("Taza de viaje", "Taza termo para café", "Viaje", 12.75));
		mercha.add(new Merchandising("Totebag con diseño floral", "Totebag primaveral", "Floral", 15.99));
		mercha.add(new Merchandising("Totebag con ilustración de gatos", "Totebag de gatitos adorables", "Animales",
				18.50));
		mercha.add(new Merchandising("Totebag minimalista", "Totebag elegante", "Moderno", 12.75));
		mercha.add(new Merchandising("Totebag de lona", "Totebag resistente", "Básico", 10.99));
		mercha.add(new Merchandising("Agenda semanal", "Agenda clásica", "Básico", 9.99));
		mercha.add(new Merchandising("Agenda de estilo vintage", "Agenda retro", "Vintage", 12.50));
		mercha.add(new Merchandising("Agenda con diseño floral", "Agenda primaveral", "Floral", 14.75));
		mercha.add(new Merchandising("Agenda de piel", "Agenda elegante", "Moderno", 20.99));
		mercha.add(new Merchandising("Agenda minimalista", "Agenda simple", "Básico", 8.99));
		mercha.add(new Merchandising("Llavero con diseño de gatito", "Llavero gatito", "Animales", 5.99));
		mercha.add(new Merchandising("Llavero con forma de corazón", "Llavero corazón", "Romántico", 3.50));
		mercha.add(new Merchandising("Llavero con diseño floral", "Llavero floral", "Floral", 4.75));
		mercha.add(new Merchandising("Boli de tinta azul", "Boli azul", "Azul", 1.99));
		mercha.add(new Merchandising("Boli de tinta roja", "Boli rojo", "Rojo", 1.50));
		mercha.add(new Merchandising("Boli de tinta verde", "Boli verde", "Verde", 2.75));
		mercha.add(new Merchandising("Boli de tinta rosa", "Boli rosa", "Rosa", 1.25));
		mercha.add(new Merchandising("Boli de tinta morada", "Boli morado", "Morado", 1.99));
		mercha.add(new Merchandising("Estuche de diseño floral", "Estuche primaveral", "Floral", 8.99));
		mercha.add(new Merchandising("Estuche de estilo vintage", "Estuche retro", "Vintage", 12.50));
		mercha.add(new Merchandising("Estuche minimalista", "Estuche simple", "Básico", 5.75));
		mercha.add(new Merchandising("Set de ajedrez", "Ajedrez clásico", "Estrategia", 29.99));
		mercha.add(new Merchandising("Juego de mesa de cartas", "Baraja de cartas", "Cartas", 9.50));
		mercha.add(new Merchandising("Juego de mesa familiar", "Monopoly clásico", "Familiar", 24.75));
		mercha.add(new Merchandising("Juego de mesa de aventuras", "Catan", "Aventura", 34.99));
		mercha.add(new Merchandising("Juego de mesa de estrategia", "Risk", "Estrategia", 27.99));
		return mercha;
	}

	public static ArrayList<Exposiciones> variasExposiciones(ArrayList<Merchandising> mercha) {
		ArrayList<Exposiciones> listaExposiciones = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date inicioPrincipito = null, finPrincipito = null;
		Date inicioCocina = null, finCocina = null;
		Date inicioMascotas = null, finMascotas = null;
		Date inicioInformatica = null, finInformatica = null;
		Date inicioSalud = null, finSalud = null;
		Date inicioDragones = null, finDragones = null;

		try {
			inicioPrincipito = sdf.parse("2024-20-06");
			finPrincipito = sdf.parse("2024-20-07");
			inicioCocina = sdf.parse("2024-07-01");
			finCocina = sdf.parse("2024-07-30");
			inicioMascotas = sdf.parse("2024-08-01");
			finMascotas = sdf.parse("2024-08-30");
			inicioInformatica = sdf.parse("2024-09-01");
			finInformatica = sdf.parse("2024-09-30");
			inicioSalud = sdf.parse("2024-10-01");
			finSalud = sdf.parse("2024-10-30");
			inicioDragones = sdf.parse("2024-11-01");
			finDragones = sdf.parse("2024-11-30");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ArrayList<Merchandising> merchaPrincipito = getRandomMerchandising(mercha, 5);
		ArrayList<Merchandising> merchaCocina = getRandomMerchandising(mercha, 5);
		ArrayList<Merchandising> merchaMascotas = getRandomMerchandising(mercha, 5);
		ArrayList<Merchandising> merchaInformatica = getRandomMerchandising(mercha, 5);
		ArrayList<Merchandising> merchaSalud = getRandomMerchandising(mercha, 5);
		ArrayList<Merchandising> merchaDragones = getRandomMerchandising(mercha, 5);

		Exposiciones expoPrincipito = new Exposiciones("Exposición de El Principito", 20,
				"El cosmos y el universo desde el punto de vista de El Principito", merchaPrincipito, inicioPrincipito,
				finPrincipito);

		Exposiciones expoCocina = new Exposiciones("Exposición de Cocina de Alta Calidad", 20,
				"Los secretos de la cocina gourmet", merchaCocina, inicioCocina, finCocina);

		Exposiciones expoMascotas = new Exposiciones("Exposición de Mascotas", 20,
				"El mundo de las mascotas y su cuidado", merchaMascotas, inicioMascotas, finMascotas);

		Exposiciones expoInformatica = new Exposiciones("Exposición de Informática", 20,
				"Innovaciones en tecnología y computación", merchaInformatica, inicioInformatica, finInformatica);

		Exposiciones expoSalud = new Exposiciones("Exposición de Salud", 20, "Avances en medicina y bienestar",
				merchaSalud, inicioSalud, finSalud);

		Exposiciones expoDragones = new Exposiciones("Exposición de Dragones", 20, "Mitos y leyendas sobre dragones",
				merchaDragones, inicioDragones, finDragones);

		Collections.addAll(listaExposiciones, expoPrincipito, expoCocina, expoMascotas, expoInformatica, expoSalud,
				expoDragones);

		return listaExposiciones;

	}

	public static ArrayList<Merchandising> getRandomMerchandising(ArrayList<Merchandising> mercha, int count) {
		Collections.shuffle(mercha);
		return new ArrayList<>(mercha.subList(0, Math.min(count, mercha.size())));
	}	
}