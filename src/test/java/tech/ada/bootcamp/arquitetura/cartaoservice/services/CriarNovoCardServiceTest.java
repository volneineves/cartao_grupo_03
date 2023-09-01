package tech.ada.bootcamp.arquitetura.cartaoservice.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Card;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.RequestUser;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CardRepository;

@ExtendWith(MockitoExtension.class)
public class CriarNovoCardServiceTest {

    @Mock
    private CardRepository cartaoRepository;

    @InjectMocks
    private CardService criarNovoCartaoService;



    @Test
    void shouldSaveSuccessfullyANewCard(){
        RequestUser cadastroUsuarioRequest = Mockito.mock(RequestUser.class);

//        Mockito.when(cadastroUsuarioRequest.getTipoCartao()).thenReturn(TipoCartao.OURO);
//        Mockito.when(cadastroUsuarioRequest.getIdentificador()).thenReturn("00000000000");
//        Mockito.when(cadastroUsuarioRequest.getNome()).thenReturn("Jose Joao da Silva");

        //TODO arrumar teste, ta recebendo de parametro um cadastroUsuarioRequest, mas refatoramos pra receber um CartaoRequest
        //criarNovoCartaoService.execute(cadastroUsuarioRequest);

        ArgumentCaptor<Card> cartaoArgumentCaptor = ArgumentCaptor.forClass(Card.class);
        Mockito.verify(cartaoRepository,Mockito.times(1))
                .save(cartaoArgumentCaptor.capture());
        Card cardSalvo = cartaoArgumentCaptor.getValue();
//        Assertions.assertEquals(LocalDate.now().plusYears(5),
//                cardSalvo.getVencimentoCartao());
//        Assertions.assertEquals(3, cardSalvo.getCodigoSeguranca().length());
//        Assertions.assertEquals(12, cardSalvo.getNumeroCartao().length());
    }
}
