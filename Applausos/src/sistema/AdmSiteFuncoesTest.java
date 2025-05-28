package sistema;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import usuario.InfoPeca;
import usuario.User;
import usuario.UserType;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AdmSiteFuncoesTest {

    static class UsuarioTeste extends User {
        public UsuarioTeste(String nome, String email, String telefone, String cpf, String senha, UserType tipo) {
            super(nome, email, telefone, cpf, senha, tipo);
        }
    }

    @Test
    public void testCadastrarPecaSimulada() {
        InfoPeca peca = new InfoPeca("Peça Teste", LocalDate.of(2025, 6, 10), 100.0, "Teatro Central");
        AdmSiteFuncoes.getListaPeca()[0] = peca;

        InfoPeca recuperada = AdmSiteFuncoes.getListaPeca()[0];
        assertEquals("Peça Teste", recuperada.getNome());
        assertEquals(100.0, recuperada.getValor());
        assertEquals("Teatro Central", recuperada.getLocal());
        assertEquals(LocalDate.of(2025, 6, 10), recuperada.getData());
    }

    @Test
    public void testTotalPecasSimulado() {
        InfoPeca peca = new InfoPeca("Peça Extra", LocalDate.of(2025, 7, 1), 150.0, "Teatro Norte");
        AdmSiteFuncoes.getListaPeca()[1] = peca;

        assertNotNull(AdmSiteFuncoes.getListaPeca()[1]);
    }

    @Test
    public void testOpcaoAdmSite() {
        List<User> usuarios = new ArrayList<>();
        usuarios.add(new UsuarioTeste("Maria", "maria@email.com", "9999", "123", "senha", UserType.CLIENTE));
        usuarios.add(new UsuarioTeste("João", "joao@email.com", "8888", "456", "senha", UserType.CLIENTE));

        assertDoesNotThrow(() -> AdmSiteFuncoes.opcaoAdmSite(usuarios));
    }

    @Test
    public void testCadastrarPeca() {

        AdmSiteFuncoes.getListaPeca()[0] = null;
        resetIndiceAdmSite();

        String input = "Hamlet\n25\n12\n2025\n150.0\nTeatro Municipal\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AdmSiteFuncoes.ENTRADA = new Scanner(System.in).useLocale(Locale.US);

        AdmSiteFuncoes.cadastrarPeca();

        InfoPeca[] lista = AdmSiteFuncoes.getListaPeca();
        int total = AdmSiteFuncoes.getTotalPecas();

        assertEquals(1, total);
        assertEquals("Hamlet", lista[0].getNome());
        assertEquals(LocalDate.of(2025, 12, 25), lista[0].getData());
        assertEquals(150.0, lista[0].getValor(), 0.01);
        assertEquals("Teatro Municipal", lista[0].getLocal());
    }

    private void resetIndiceAdmSite() {
        try {
            java.lang.reflect.Field field = AdmSiteFuncoes.class.getDeclaredField("indice");
            field.setAccessible(true);
            field.setInt(null, 0);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao resetar índice de AdmSiteFuncoes", e);
        }
    }
}
