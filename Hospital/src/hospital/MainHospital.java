package hospital;

public class MainHospital {

	private static ConectorHospital carlosHaya = new ConectorHospital();

	public static void main(String[] args) {
		int eleccionMenu;

		do {
			eleccionMenu = MenusHospital.menuPrincipal();
			MenusHospital.opcionesMenuPrincipal(eleccionMenu);
		} while (eleccionMenu != 4);
	}
}
