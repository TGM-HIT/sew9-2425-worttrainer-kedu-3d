import org.junit.jupiter.api.Test;
import worttrainer.Model.Worttrainer;
import worttrainer.View.Worttraining;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorttrainingTest {

    @Test
    public void testViewInitialisierung() {
        Worttrainer trainer = new Worttrainer();
        Worttraining view = new Worttraining(null, trainer);

        assertNotNull(view, "Die View sollte initialisiert werden.");
        assertTrue(view.getTextfield().isEmpty(), "Das Textfeld sollte initial leer sein.");
    }
}
