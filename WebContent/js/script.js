$(document).ready(function(){
	 /* O primeiro passo é esconder todas as divs que contenham formulários*/	
	  $(".form-section").hide();	
		/*A função é invocada toda vez que um elemento com a classe btn que esteja dentro do container
		staging-buttons é clicado*/
	  $(".staging-buttons .btn").on('click', function(event) {
	  	/*a expressão regular isola o numero presente no ID do botão clicado*/
          var btnId = this.id.match(/\d+/)[0];
		/* esse comando esconde todos os formulários da da sessão form-section */
          $(".form-section").hide();
          /*esse comando exibe a div de id correspondente ao botão clicado*/
          $("#form_" + btnId).show();
          /*Essas duas linhas alteram a fonte de todos os botões da sessão esquerda e depois atribuem
          um BOLD a fonte do botão selecionado*/
          $(".staging-buttons .btn").css("font-weight", "normal");
          $(this).css("font-weight", "bold");
          /*Isso está comentado ao ponto de causar demência em quem já sabe alguma coisa de jquery
          por que o eclipse se recusa a rodar meus scripts e eu não queria ficar maluco sozinho.*/
      });
});