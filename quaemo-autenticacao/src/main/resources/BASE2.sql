CREATE VIEW BD_BASI.VW_ACCES_TOKEN_AUTENTICADO
AS ( 
SELECT TOK.DS_ACCES_TOKEN,TOK.DS_TIPO_TOKEN,RS.CD_RECURSO_SISTEMA,RS.DS_URL_RECURSO_SISTEMA,PRS.NM_METODO,TOK.FL_VALIDO,U.CD_USUARIO,U.NM_USUARIO,U.NM_LOGIN,PA.CD_PERFIL_ACESSO,TRIM(PA.NM_PERFIL_ACESSO) AS NM_PERFIL_ACESSO,TRIM(PA.FL_TIPO_PERFIL) AS FL_TIPO_PERFIL,TOK.CD_SISTEMA,TOK.NR_IP,TOK.DS_BROWSER,TOK.DS_VERSAO_BROWSER , TOK.DS_SISTEMA_OPERACIONAL,AC.FL_VALIDA_ORIGEM_TOKEN  FROM
TB_RECURSO_SISTEMA RS JOIN 
RL_PERFIL_RECURSO_SISTEMA PRS ON (PRS.CD_RECURSO_SISTEMA=RS.CD_RECURSO_SISTEMA) JOIN
RL_USUARIO_PERFIL_ACESSO UPA ON (UPA.CD_PERFIL_ACESSO=PRS.CD_PERFIL_ACESSO) JOIN 
TB_PERFIL_ACESSO PA ON (PA.CD_PERFIL_ACESSO=UPA.CD_PERFIL_ACESSO) JOIN 
TB_ACCES_TOKEN TOK ON (TOK.CD_USUARIO=UPA.CD_USUARIO AND TOK.CD_SISTEMA=PA.CD_SISTEMA) JOIN
TB_APLICACAO_CLIENTE AC ON (AC.ID_APLICACAO_CLIENTE=TOK.ID_APLICACAO_CLIENTE) JOIN 
TB_USUARIO U ON (U.CD_USUARIO=TOK.CD_USUARIO)
WHERE
RS.FL_ATIVO='1' AND UPA.FL_EXCLUIDO='0'
UNION ALL
SELECT TOK.DS_ACCES_TOKEN,TOK.DS_TIPO_TOKEN,RS.CD_RECURSO_SISTEMA,RS.DS_URL_RECURSO_SISTEMA,RLAR.NM_METODO,TOK.FL_VALIDO,NVL(TOK.CD_USUARIO,0) AS CD_USUARIO,NVL(U.NM_USUARIO,AC.NM_APLICACAO_CLIENTE),U.NM_LOGIN,PA.CD_PERFIL_ACESSO,TRIM(PA.NM_PERFIL_ACESSO) AS NM_PERFIL_ACESSO,TRIM(PA.FL_TIPO_PERFIL) AS FL_TIPO_PERFIL,TOK.CD_SISTEMA,TOK.NR_IP,TOK.DS_BROWSER,TOK.DS_VERSAO_BROWSER , TOK.DS_SISTEMA_OPERACIONAL,AC.FL_VALIDA_ORIGEM_TOKEN  FROM
TB_RECURSO_SISTEMA RS JOIN 
RL_APLICACAO_RECURSO_SISTEMA RLAR ON (RLAR.CD_RECURSO_SISTEMA=RS.CD_RECURSO_SISTEMA) JOIN
TB_APLICACAO_CLIENTE AC ON (AC.ID_APLICACAO_CLIENTE=RLAR.ID_APLICACAO_CLIENTE) JOIN 
TB_ACCES_TOKEN TOK ON (TOK.ID_APLICACAO_CLIENTE=AC.ID_APLICACAO_CLIENTE AND TOK.CD_SISTEMA=AC.CD_SISTEMA) LEFT JOIN 
TB_USUARIO U ON (U.CD_USUARIO=TOK.CD_USUARIO) LEFT JOIN 
RL_USUARIO_PERFIL_ACESSO UPA ON (UPA.CD_USUARIO=U.CD_USUARIO AND UPA.FL_EXCLUIDO='0' ) LEFT JOIN 
TB_PERFIL_ACESSO PA ON (PA.CD_PERFIL_ACESSO=UPA.CD_PERFIL_ACESSO)
WHERE
RS.FL_ATIVO='1' 
)




-- Unable to render VIEW DDL for object BD_BASI.VW_MENU_USUARIO_SISTEMA with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_MENU_USUARIO_SISTEMA
AS (
SELECT 
M.CD_MENU,M.NM_MENU,M.DS_MENU,M.NR_ITEM_MENU,M.NR_ORDEM_MENU,M.DS_URL_PAGINA,M.NM_ICO_MENU,M.DS_STYLE_MENU,M.FL_VISIVEL,M.CD_MENU_SUPERIOR,RLUP.CD_USUARIO,PA.CD_PERFIL_ACESSO,PA.NM_PERFIL_ACESSO,PA.CD_SISTEMA
FROM
TB_MENU M JOIN
RL_PERFIL_ACESSO_MENU PAM ON (PAM.CD_MENU=M.CD_MENU) JOIN
TB_PERFIL_ACESSO PA ON (PA.CD_PERFIL_ACESSO=PAM.CD_PERFIL_ACESSO) JOIN 
RL_USUARIO_PERFIL_ACESSO RLUP ON (RLUP.CD_PERFIL_ACESSO=PA.CD_PERFIL_ACESSO AND RLUP.FL_EXCLUIDO='0')
)





-- Unable to render VIEW DDL for object BD_BASI.VW_PERMISSAO_RECURSO with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_PERMISSAO_RECURSO
AS ( 
SELECT DISTINCT CD_RECURSO_SISTEMA||NM_METODO AS CD_PERMISSAO, CD_RECURSO_SISTEMA,NM_METODO FROM RL_PERFIL_RECURSO_SISTEMA
 )





-- Unable to render VIEW DDL for object BD_BASI.VW_PERMISSAO_RECURSO_PERFIL with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_PERMISSAO_RECURSO_PERFIL
AS ( 
   SELECT CD_RECURSO_SISTEMA||NM_METODO||CD_PERFIL_ACESSO AS ID, CD_RECURSO_SISTEMA||NM_METODO AS CD_PERMISSAO,  CD_PERFIL_ACESSO FROM RL_PERFIL_RECURSO_SISTEMA
)




-- Unable to render VIEW DDL for object BD_BASI.VW_USUARIO_RESPONSAVEL with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_USUARIO_RESPONSAVEL
AS ( 
SELECT CD_USUARIO, NM_USUARIO,NM_LOGIN,DS_EMAIL FROM TB_USUARIO 
)




-- Unable to render VIEW DDL for object BD_BASI.VW_USUARIO_SISTEMA with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_USUARIO_SISTEMA
AS (
 SELECT U.CD_USUARIO
       ,U.NM_USUARIO
       ,U.NM_LOGIN
       ,U.DS_EMAIL
       ,U.NR_CPF
       ,U.NR_CNPJ
       ,U.NR_DDD_TELEFONE
       ,U.NR_TELEFONE
       ,U.NR_DDD_CELULAR,U.NR_CELULAR,U.FL_INTERNO,U.DT_ULTIMO_LOGON,U.NR_TENTATIVAS_LOGON,U.FL_BLOQUEADO,U.DS_SENHA_TEMP,U.DT_SENHA_TEMP,U.FL_TROCA_SENHA
       ,RL.FL_ATIVO,U.FOTO,U.DT_CADASTRO,U.CD_RESPONSAVEL_CADASTRO,U.DT_ATUALIZACAO,U.CD_RESPONSAVEL_ATUALIZACAO,U.FL_EXCLUIDO,PA.CD_SISTEMA,PA.NM_PERFIL_ACESSO,PA.CD_PERFIL_ACESSO,PA.FL_TIPO_PERFIL,PA.NM_PAGINA_PRINCIPAL,S.NM_SISTEMA,U.DS_CARGO,RL.FL_RECEBE_EMAIL,RL.FL_ASSINANTE  
 FROM
TB_USUARIO U LEFT JOIN
RL_USUARIO_PERFIL_ACESSO RL ON (U.CD_USUARIO=RL.CD_USUARIO) LEFT JOIN 
TB_PERFIL_ACESSO PA ON (PA.CD_PERFIL_ACESSO=RL.CD_PERFIL_ACESSO)LEFT JOIN 
TB_SISTEMA S ON (S.CD_SISTEMA=PA.CD_SISTEMA)
WHERE RL.FL_EXCLUIDO='0'
)





-- Unable to render VIEW DDL for object BD_BASI.VW_USUARIO_SISTEMA_ACESSO with DBMS_METADATA attempting internal generator.
CREATE VIEW BD_BASI.VW_USUARIO_SISTEMA_ACESSO
AS (
SELECT DISTINCT U.CD_USUARIO,
U.NM_USUARIO,
U.NM_LOGIN,
U.DS_EMAIL,
U.NR_CPF,
U.NR_CNPJ,  
U.NR_DDD_TELEFONE,
U.NR_TELEFONE,
U.NR_DDD_CELULAR,
U.NR_CELULAR,  
U.FL_INTERNO,     
U.DT_ULTIMO_LOGON ,      
U.NR_TENTATIVAS_LOGON ,  
U.FL_BLOQUEADO ,
U.DS_SENHA_TEMP,
U.DT_SENHA_TEMP,   
U.FL_TROCA_SENHA,
U.FL_ATIVO,
NULL AS FOTO,
U.DT_CADASTRO,
U.CD_RESPONSAVEL_CADASTRO,
U.DT_ATUALIZACAO ,
U.CD_RESPONSAVEL_ATUALIZACAO,
U.FL_EXCLUIDO,
U.CD_SISTEMA,
U.NM_PERFIL_ACESSO ,
U.CD_PERFIL_ACESSO,
U.FL_TIPO_PERFIL ,
U.NM_PAGINA_PRINCIPAL ,
U.NM_SISTEMA,
U.DS_CARGO,
U.FL_RECEBE_EMAIL,
U.FL_ASSINANTE,
UA.CD_USUARIO AS CD_USUARIO_ACESSO 
         FROM 
         TB_USUARIO UA, 
         VW_USUARIO_SISTEMA U JOIN 
         VW_USUARIO_SISTEMA_ENTIDADE E ON (E.CD_USUARIO=U.CD_USUARIO) 
         WHERE  
         U.FL_EXCLUIDO='0' AND E.CD_ENTIDADE_NACIONAL IN (SELECT CD_ENTIDADE_NACIONAL 
         FROM VW_USUARIO_ACESSO_ENTIDADE WHERE SG_CATEGORIA_USUARIO='DN' AND  CD_USUARIO=UA.CD_USUARIO AND CD_SISTEMA=U.CD_SISTEMA )
         UNION ALL
         SELECT DISTINCT
         U.CD_USUARIO,
U.NM_USUARIO,
U.NM_LOGIN,
U.DS_EMAIL,
U.NR_CPF,
U.NR_CNPJ,  
U.NR_DDD_TELEFONE,
U.NR_TELEFONE,
U.NR_DDD_CELULAR,
U.NR_CELULAR,  
U.FL_INTERNO,     
U.DT_ULTIMO_LOGON ,      
U.NR_TENTATIVAS_LOGON ,  
U.FL_BLOQUEADO ,
U.DS_SENHA_TEMP,
U.DT_SENHA_TEMP,   
U.FL_TROCA_SENHA,
U.FL_ATIVO,
NULL AS FOTO,
U.DT_CADASTRO,
U.CD_RESPONSAVEL_CADASTRO,
U.DT_ATUALIZACAO ,
U.CD_RESPONSAVEL_ATUALIZACAO,
U.FL_EXCLUIDO,
U.CD_SISTEMA,
U.NM_PERFIL_ACESSO ,
U.CD_PERFIL_ACESSO,
U.FL_TIPO_PERFIL ,
U.NM_PAGINA_PRINCIPAL ,
U.NM_SISTEMA,
U.DS_CARGO,
U.FL_RECEBE_EMAIL,
U.FL_ASSINANTE,
UA.CD_USUARIO AS CD_USUARIO_ACESSO 
         FROM 
         TB_USUARIO UA, 
         VW_USUARIO_SISTEMA U JOIN 
         VW_USUARIO_SISTEMA_ENTIDADE E ON (E.CD_USUARIO=U.CD_USUARIO) 
         WHERE   
         U.FL_EXCLUIDO='0' AND E.CD_ENTIDADE_REGIONAL IN (SELECT CD_ENTIDADE_REGIONAL 
         FROM VW_USUARIO_ACESSO_ENTIDADE WHERE SG_CATEGORIA_USUARIO='DR' AND  CD_USUARIO=UA.CD_USUARIO AND CD_SISTEMA=U.CD_SISTEMA )
         UNION ALL
         SELECT DISTINCT
     U.CD_USUARIO,
U.NM_USUARIO,
U.NM_LOGIN,
U.DS_EMAIL,
U.NR_CPF,
U.NR_CNPJ,  
U.NR_DDD_TELEFONE,
U.NR_TELEFONE,
U.NR_DDD_CELULAR,
U.NR_CELULAR,  
U.FL_INTERNO,     
U.DT_ULTIMO_LOGON ,      
U.NR_TENTATIVAS_LOGON ,  
U.FL_BLOQUEADO ,
U.DS_SENHA_TEMP,
U.DT_SENHA_TEMP,   
U.FL_TROCA_SENHA,
U.FL_ATIVO,
NULL AS FOTO,
U.DT_CADASTRO,
U.CD_RESPONSAVEL_CADASTRO,
U.DT_ATUALIZACAO ,
U.CD_RESPONSAVEL_ATUALIZACAO,
U.FL_EXCLUIDO,
U.CD_SISTEMA,
U.NM_PERFIL_ACESSO ,
U.CD_PERFIL_ACESSO,
U.FL_TIPO_PERFIL ,
U.NM_PAGINA_PRINCIPAL ,
U.NM_SISTEMA,
U.DS_CARGO,
U.FL_RECEBE_EMAIL,
U.FL_ASSINANTE,
UA.CD_USUARIO AS CD_USUARIO_ACESSO 
         FROM 
         TB_USUARIO UA, 
         VW_USUARIO_SISTEMA U JOIN 
         VW_USUARIO_SISTEMA_ENTIDADE E ON (E.CD_USUARIO=U.CD_USUARIO) 
         WHERE  
         U.FL_EXCLUIDO='0' AND E.CD_UNIDADE_ATENDIMENTO_DR IN (SELECT CD_UNIDADE_ATENDIMENTO_DR 
         FROM VW_USUARIO_ACESSO_ENTIDADE WHERE SG_CATEGORIA_USUARIO='UA' AND  CD_USUARIO=UA.CD_USUARIO AND CD_SISTEMA=U.CD_SISTEMA )
        )










