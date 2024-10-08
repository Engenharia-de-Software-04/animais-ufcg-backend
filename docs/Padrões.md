# Padrões do GitHub

## Branches

Nomes de branches são compostos de duas partes:

1. **Categoria**

   - **docs**: Mudanças na documentação
   - **feat**: Nova funcionalidade
   - **fix**: Correção de um bug
   - **refactor**: Mudança de código que não adiciona uma funcionalidade e também não corrige um bug, apenas refatoração
   - **test**: Adicionar ou corrigir testes
   - **style**: Mudanças no código que não afetam seu significado (espaço em branco, formatação, ponto e vírgula, etc.)

2. **Descrição curta e clara da tarefa ou funcionalidade**

   Use hífen para separar palavras e mantenha o nome da branch conciso e descritivo. Em inglês.

   Cada branch corresponde a uma US (User Story).

   Crie a branch a partir de `dev`.

   Após a aprovação do PR (Pull Request), o responsável pela branch deve fazer o pull, resolver os conflitos e, em seguida, fazer o merge.

   Exemplos:

```plaintext
feat/USXX-adding-animal
fix/USXX-listing-animals
style/USXX-fixing-indentation
```

   ## Commits

São compostos de 

1) Categorias (mesmas das branches)

- **docs**: Mudanças na documentação
- **feat**: Nova funcionalidade;
- **fix**: Correção de um bug;
- **refactor**: Mudança de código que não adiciona uma funcionalidade e também não corrigi um bug, apenas refatorar.
- **test**: Adicionar ou corrigir testes.
- **style:** mudanças no código que não afetam seu significado (espaço em branco, formatação, ponto e vírgula, etc);

2) Verbos no gerúndio (adding, fixing, listing). 

Exemplos:

`feat: Adding a method to list animals`  

`fix: fixing route of the method get animal` 

## Nomeação

Nomeação de variáveis em camelCase

Variável em inglês

Código em inglês
