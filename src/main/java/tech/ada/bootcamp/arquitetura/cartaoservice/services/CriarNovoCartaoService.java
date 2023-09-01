package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CartaoResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CriarNovoCartaoService {
    private final CartaoRepository cartaoRepository;
    private static Random random;

    //TODO retornar CartaoResponse
    public Cartao execute(CartaoRequest cartaoRequest){
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();

        cartao.setTipoCartao(tipoCartaoAleatorio());

        //TODO aqui mesmo?
        //Usuario usuario =  new Usuario();
        //usuario.setIdentificador(cadastroUsuarioRequest.getIdentificador());

        cartao.setUsuario(cartaoRequest.getUsuario());
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(cartao.getNomeTitular());
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        return cartaoRepository.save(cartao);
    }

    private String gerarNumeroAleatorio(int length) {

        final int tamanho = length<=0?1:length;
        IntStream stream =  getRandom().ints(tamanho,0,9);
        StringBuilder builder = new StringBuilder();
        stream.forEachOrdered(builder::append);
        return builder.toString();
    }

    private static Random getRandom(){
        if(Objects.isNull(random)){
            random = new Random();
        }
        return random;
    }

    private TipoCartao tipoCartaoAleatorio(){
        TipoCartao[] tipoCartaos = TipoCartao.values();
        random = getRandom();
        int index = random.nextInt(tipoCartaos.length);
        TipoCartao tipoCartao = tipoCartaos[index];
        return tipoCartao;
    }

    public CartaoResponse getOne(String numeroCartao){
        Optional<Cartao> cartaoOptional = this.cartaoRepository.findById(numeroCartao);
        return cartaoOptional.get().dto();
    }

    //TODO verificar se essa é a melhor forma de fazer isso
    public List<CartaoResponse> getAllByUser(String identificador){
        return cartaoRepository.findCartaoByUsuario_Identificador(identificador)
                .stream()
                .map(cartao -> cartao.dto()).collect(Collectors.toList());
    }

    public CartaoResponse edit(CartaoRequest cartaoRequest){
        Cartao cartao = cartaoRepository.findCartaoByNumeroCartao(cartaoRequest.getNumeroCartao());

        //TODO melhorar isso ai
        if(cartao == null){
            throw new EntityNotFoundException("Este cartão não foi encontrado no banco de dados.");
        }

        cartao.setNumeroCartao(cartaoRequest.getNumeroCartao());
        cartao.setNomeTitular(cartaoRequest.getNomeTitular());
        cartao.setVencimentoCartao(cartaoRequest.getVencimentoCartao());
        cartao.setCodigoSeguranca(cartaoRequest.getCodigoSeguranca());
        cartao.setTipoCartao(cartaoRequest.getTipoCartao());
        cartao.setIdContaBanco(cartao.getIdContaBanco());
        cartao.setDependente(cartaoRequest.getDependente());
        cartao.setUsuario(cartaoRequest.getUsuario());

        cartao = cartaoRepository.save(cartao);

        return cartao.dto();

    }

    //TODO patch

    public String delete(String numeroCartao){
        cartaoRepository.deleteById(numeroCartao);
        return "Cartão " + numeroCartao + "deletado com sucesso";
    }



}
