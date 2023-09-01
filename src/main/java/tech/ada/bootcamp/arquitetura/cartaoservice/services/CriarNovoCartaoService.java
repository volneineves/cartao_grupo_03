package tech.ada.bootcamp.arquitetura.cartaoservice.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Cartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.entities.Usuario;
import tech.ada.bootcamp.arquitetura.cartaoservice.exceptions.CartaoNaoEncontradoException;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.TipoCartao;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CadastroUsuarioRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.request.CartaoRequest;
import tech.ada.bootcamp.arquitetura.cartaoservice.payloads.response.CartaoResponse;
import tech.ada.bootcamp.arquitetura.cartaoservice.repositories.CartaoRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class CriarNovoCartaoService {
    private final CartaoRepository cartaoRepository;
    private static Random random;


    //TODO retornar CartaoResponse
    public CartaoResponse execute(CartaoRequest cartaoRequest){
        LocalDate dataAtual = LocalDate.now();
        Cartao cartao = new Cartao();

        cartao.setTipoCartao(tipoCartaoAleatorio());

        //TODO aqui mesmo?
        Usuario usuario =  new Usuario();
        usuario.setId(cartaoRequest.getUsuarioId());

        cartao.setUsuario(usuario);
        cartao.setIdContaBanco(UUID.randomUUID().toString());
        cartao.setNomeTitular(cartaoRequest.getNomeTitular());
        cartao.setVencimentoCartao(dataAtual.plusYears(5));
        cartao.setCodigoSeguranca(gerarNumeroAleatorio(3));
        cartao.setNumeroCartao(gerarNumeroAleatorio(12));
        cartao = cartaoRepository.save(cartao);
        return cartao.dto();
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

    public CartaoResponse getOne(UUID id){
        Optional<Cartao> cartaoOptional = this.cartaoRepository.findById(id);

        if(cartaoOptional.isEmpty()){
            throw new EntityNotFoundException("Este cartão não foi encontrado no banco de dados.");
        }

        return cartaoOptional.get().dto();
    }

    //TODO verificar se essa é a melhor forma de fazer isso
    public List<CartaoResponse> getAllByUser(UUID id){
        return cartaoRepository.findCartaoByUsuarioId(id)
                .stream()
                .map(cartao -> cartao.dto()).collect(Collectors.toList());
    }

    public CartaoResponse edit(UUID id, CartaoRequest cartaoRequest){
        Optional<Cartao> cartaoOp = cartaoRepository.findById(id);

        if(cartaoOp.isEmpty()){
            throw new CartaoNaoEncontradoException();
        }

        Cartao cartao = cartaoOp.get();

        Usuario usuario =  new Usuario();
        usuario.setId(cartaoRequest.getUsuarioId());

        cartao.setUsuario(usuario);
        cartao.setNumeroCartao(cartaoRequest.getNumeroCartao());
        cartao.setNomeTitular(cartaoRequest.getNomeTitular());
        cartao.setVencimentoCartao(cartaoRequest.getVencimentoCartao());
        cartao.setCodigoSeguranca(cartaoRequest.getCodigoSeguranca());
        cartao.setTipoCartao(cartaoRequest.getTipoCartao());
        cartao.setIdContaBanco(cartao.getIdContaBanco());
        cartao.setDependente(cartaoRequest.getDependente());

        cartao = cartaoRepository.save(cartao);

        return cartao.dto();

    }

    //TODO patch

    public String delete(UUID id){
        cartaoRepository.deleteById(id);
        return "Cartão " + id + " deletado com sucesso";
    }



}
