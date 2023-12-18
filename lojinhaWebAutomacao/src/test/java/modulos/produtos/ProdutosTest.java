package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de ProdutosTest")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar até a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProdutos()
                .informarONomeDoProduto("Atari 3.0")
                .informarOValorDoProduto("000")
                .informarACorDoProduto("Cinza, Preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor acima de 7mil")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProdutos()
                .informarONomeDoProduto("Xbox One")
                .informarOValorDoProduto("7000,01")
                .informarACorDoProduto("Cinza, Preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso registrar um produto com valor entre 0 e 7 mil")
    public void testEPermitidoRegistrarProdutoComValorEntreZeroESeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProdutos()
                .informarONomeDoProduto("Atari 3.0")
                .informarOValorDoProduto("4999,00")
                .informarACorDoProduto("Cinza, Preto")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresentada();
    }

    @Test
    @DisplayName("Posso registrar um produto com valor de 1 centavo")
    public void testEPermitidoRegistrarProdutoComValorDeUmCentavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProdutos()
                .informarONomeDoProduto("Play Station 3.0")
                .informarOValorDoProduto("1")
                .informarACorDoProduto("Branco")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresentada();
    }

    @Test
    @DisplayName("Posso registrar um produto com valor de 7 mil")
    public void testEPermitidoRegistrarProdutoComValorDeSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProdutos()
                .informarONomeDoProduto("Play Station 3.0")
                .informarOValorDoProduto("7000,00")
                .informarACorDoProduto("Branco")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresentada();
    }
    @AfterEach
    public void afterEach(){
        navegador.quit();
    }
}
