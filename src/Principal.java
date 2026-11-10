import controllers.HeedTechController;
import exceptions.AplicacaoException;
import exceptions.ClienteException;
import exceptions.GadoException;

public class Principal {
	static void main() throws ClienteException, GadoException, AplicacaoException {
		HeedTechController heedTechController = new HeedTechController();

		heedTechController.iniciar();
	}
}

// Dados inicias de Clientes
//		listaCliente.add(new Cliente("Guilherme", "106.394.969-62", "17/10/1998", "Fazenda do Gui", "Rua A"));
//		listaCliente.add(new Cliente("Vinicius", "123.456.789-00", "18/03/2000", "Fazenda do Vini", "Rua B"));
//		listaCliente.add(new Cliente("Gabriel", "789.456.132-00", "06/07/2001", "Fazenda do Gabi", "Rua C"));
//		listaCliente.add(new Cliente("Lucas", "147.258.369-00", "16/01/2024", "Fazenda do Luca", "Rua D"));

// Dados inicias de Gados
//		listaGado.add(new Gado(listaCliente.get(0), "Mimosa", "Nelore", "Femia", "05/06/2025"));
//		listaGado.add(new Gado(listaCliente.get(0), "Mumu", "Jersey", "Femia", "21/12/2025"));
//		listaGado.add(new Gado(listaCliente.get(0), "Jorge", "Jersey", "Macho", "15/03/2024"));
//		listaGado.add(new Gado(listaCliente.get(1), "Malhada", "Nelore", "Femia", "12/01/2025"));
//		listaGado.add(new Gado(listaCliente.get(1), "Estrela", "Jersey", "Femia", "05/03/2025"));
//		listaGado.add(new Gado(listaCliente.get(1), "Cigano", "Holandesa", "Macho", "19/04/2025"));
//		listaGado.add(new Gado(listaCliente.get(2), "Pintado", "Nelore", "Macho", "25/12/2024"));
//		listaGado.add(new Gado(listaCliente.get(2), "Princesa", "Jersey", "Femia", "03/09/2025"));
//		listaGado.add(new Gado(listaCliente.get(2), "Sultão", "Holandesa" ,"Macho", "22/08/2024"));
//		listaGado.add(new Gado(listaCliente.get(3), "Valentão", "Nelore", "Macho", "14/06/2025"));
//		listaGado.add(new Gado(listaCliente.get(3), "Baunilha", "Jersey", "Femia", "27/05/2025"));
//		listaGado.add(new Gado(listaCliente.get(3), "Coronel", "Holandesa", "Macho", "30/11/2024"));