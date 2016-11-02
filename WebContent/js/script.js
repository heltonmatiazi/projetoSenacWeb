$(document).ready(function(){
   
	
   /* O primeiro passo é esconder todas as divs que contenham formulários*/ 
    $("#form_1").hide();
    $("#form_2").hide();    
    /*A função é invocada toda vez que um elemento com a classe btn que esteja dentro do container
    staging-buttons é clicado*/
    $(".staging-buttons .btn").on('click', function(event) {
          var btnId = this.id.match(/\d+/)[0];
          $(".form-section").hide();
          $("#form_" + btnId).show();
          $(".staging-buttons .btn").css("font-weight", "normal");
          $(this).css("font-weight", "bold");
      });

    
    //essa sessão trata da adição de inputs dinâmicos de experiências profissionais. 
    var max_fields      = 3; //limite de input fields
    var wrapper         = $(".input-fields-wrap"); //container
    var add_button      = $(".add-field-button"); //setando botão de adicionar
    var profissionais   ='<div class="input-fields-wrap prof-wrap">'+
                          ' <div class="form-group"><label class="form-label">Empresa</label>'+
                          '<input type="text" name="empresa-1" class="field-large">'+
                          '<div class="clearfix"></div><label class="form-label">Cargo</label>'+
                          '<input type="text" name="cargo-1" class="field-large"><div class="clearfix">'+
                          '</div><label class="form-label">Tipo de Trabalho:</label>'+
                          '<select name="tipo-trabalho"><option>Selecione a titulação</option>'+
                          '<option value="estagio">Estágio</option><option value="contratacao">'+
                          'Contratação</option><option value="CLT">CLT</option>'+
                          '<option value="concurso">Concurso</option></select>'+
                          '<div class="clearfix"></div></div><label class="form-label">'+
                          'Data de Entrada</label><input type="text" name="dataEntrada">'+
                          '<div class="clearfix"></div><label class="form-label">Data de Saída'+
                          '</label><input type="text" name="dataSaida"><div class="clearfix">'+
                          '</div></div>';
    

    var cursos          =  '<div class="cursos-wrap"><div class="form-group"> <label class="form-label">Curso:</label>'+ 
                          '<input type="text" name="curso" class="field-large"> <div class="clearfix">'+
                          '</div></div><div class="form-group"> <label class="form-label">'+
                          'Tipo de Título adquirido</label> <select name="titulo">'+
                          '<option>Selecione a titulação</option> <option value="tecnologo">'+
                          'Tecnólogo</option> '+
                          '<option value="licenciatura">Licenciatura</option> <option value="bacharelado">'+
                          'Bacharelado</option>'+
                          '<option value="mestrado">Mestrado</option> <option value="doutorado">Doutorado</option> '+
                          '<option value="pos-doutorado">Pós-doutorado</option> </select> <div class="clearfix">'+
                          '</div></div><div class="form-group"> <label class="form-label">Unidade do senac</label>'+
                          '<input type="text" name="unidade-senac" class="field-large"> </div><div class="clearfix">'+
                          '</div><div class="form-group"> <label class="form-label">Ano de ingresso:</label>'+
                          '<input type="text" name="ano-ingresso"> </div><div class="clearfix">'+
                          '</div><div class="form-group"> <label class="form-label">Semestre de Ingresso</label>'+
                          '<input type="text" name="semestre-ingresso"> </div><div class="clearfix"></div>'+
                          '<div class="form-group"> <label class="form-label">Ano de Conclusão</label> '+
                          '<input type="text" name="ano-conclusao"> </div><div class="clearfix"></div>'+
                          '<div class="form-group form-hack"> <label class="form-label">Semestre de Conclusão</label>'+
                          '<input type="text" name="semestre-conclusao">'+
                          '</div><div class="clearfix"></div></div></div>'  ;                    
    var controlBtn = 1; 
    $(add_button).click(function(e){ 
        e.preventDefault();
         if(controlBtn <= max_fields){ 
            $(wrapper).append(profissionais);
            var profCounter= $(".prof-wrap input[type='text']");
            controlBtn++; 
          
            for (index = 0; index < profCounter.length; ++index) {
               profCounter[index].setAttribute('id', "InputId" + controlBtn+ [index]);
               profCounter[index].setAttribute('name', "InputName"+ controlBtn + [index]);
            };
            var selectCounter = $(".prof-wrap select option");
            for(index2 = 0; index2 < selectCounter.length; ++index2){
              selectCounter[index2].setAttribute('id', "optionId"+ controlBtn +index2);
              selectCounter[index2].setAttribute('name', "optionName"+ controlBtn +index2);
            };
            
        };
    });
    


    var maxCursoFields    = 3;
    var cursoWrapper      = $(".wrapper-cursos");
    var addCursoBtn       = $(".add-curso-button"); 

    var controlCurso = 1;

    $(addCursoBtn).click(function(e){
      e.preventDefault();
      if(controlCurso <= maxCursoFields){
        controlCurso++;
        $(cursoWrapper).append(cursos);
         var cursosCounter= $(".cursos-wrap input[type='text']");
            for (index = 0; index < cursosCounter.length; ++index) {
             cursosCounter[index].setAttribute('id', "InputCursoId" + controlCurso+ [index]);
              cursosCounter[index].setAttribute('name', "InputCursoName"+ controlCurso + [index]);
            };
            var selectCursoCounter = $(".cursos-wrap select option");
            for(index2 = 0; index2 < selectCursoCounter.length; ++index2){
              selectCursoCounter[index2].setAttribute('id', "optionId"+ controlBtn +index2);
              selectCursoCounter[index2].setAttribute('name', "optionName"+ controlBtn +index2);
            };
      };
    });
    
});



