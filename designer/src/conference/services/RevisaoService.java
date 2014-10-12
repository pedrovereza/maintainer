package conference.services;

import conference.data.Database;
import conference.model.Artigo;
import conference.model.Revisao;

public class RevisaoService {

    private Database database;

    public void assignRevisao(Artigo artigo, Integer idRevisor, double nota) {
        if (!notaValida(nota)) {
            throw new ConferenciaException();
        }

        artigo.addRevisao(new Revisao(idRevisor, nota));
    }

    private boolean notaValida(double nota) {
        return false;
    }

}
