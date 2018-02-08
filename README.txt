# Interpreteur du langage Calc en Java  
*IMT Atlantique - FILA1 - Structure et exécution des langages de programmation*  
`Vivien Louradour`  
  
## Environnement de developpement  
OS : Debian  
IDE : IntelliJ  
JDK : OpenJDK   
Version Java : 8  

## Avancement  
J'ai implémenté la piste rouge.  
Tous les tests sont validés (avec le nouveux jeu de fichiers .calc que vous nous avez envoyé par mail).  
  
## Gestion des exceptions  
3 types d'exception sont lancés selon la nature de l'erreur :   
   - UnexpectedCharacter : exception lancée lors de l'analyse lexical, dans le cas ou un caractère invalide est lu.  
   - SyntaxException : exception lancée lors de l'analyse syntaxique (ex : expression invalide).  
   - SemanticException : exception lancée lors de l'analyse semantique et du calcul de la valeur de retour (methodes eval) (ex : appel d'une fonction non définie, utilisation d'une variable non définie...).  
  
## Possibilités d'améliorations  
J'aurai aimé pouvoir renvoyer la position d'une erreur (numéro de la ligne + position dans cette ligne (numero du caractère) dans le fichier).  
Pour cela, j'aurais pu utiliser un objet LineNumberReader à la place du FileInputStream utilisé pour lire le fichier, ce qui m'aurait permis de renvoyer le numéro de ligne, mais pas la position dans la ligne. Autrement, il aurait été possible d'utiliser un compteur dans la classe Lexer, qui incrementerai le numéro de ligne et de caractère en fonction des caractères ASCII lus (espace, tabulation, retour à la ligne, etc.).  
