package worttrainer.Persistenz;

import worttrainer.Model.Worttrainer;

public interface SaveLoad {

    public void speichern(Worttrainer worttrainer, String pfad);

    public Worttrainer laden(String pfad);
}
