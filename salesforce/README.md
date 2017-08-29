Énoncé





L'une des missions d'un développeur SalesForce est de qualifier sa base de données afin de permettre aux commerciaux de travailler avec des informations pertinentes.

Dans ce challenge, vous devez valider les enregistrements issus d'une extraction de la base. Pour être valide, un enregistrement doit satisfaire 3 critères : il ne doit pas correspondre à un doublon, la format de son téléphone doit être valide et il doit appartenir à votre zone. Les façons d'évaluer les critères sont les suivantes :
- Deux lignes sont considérées comme des doublons si elles ont les mêmes nom, prénom et société.

- Un numéro de téléphone est valide s'il a le format suivant +X-YYYYYYYYYYY où X est une suite de 1 à 3 chiffres et YYYYYYYYYYY est une suite de 9 à 11 chiffres. Pour qu'un numéro soit valide il doit aussi contenir le caractère + avant X et - entre X et Y.

- Votre zone est un ensemble de pays. L'enregistrement correspond à votre zone si son pays est inclus dans votre liste de pays.




Format des données



Entrée



Ligne 1 : un entier N compris entre 5 et 500 représentant le nombre de lignes dans l'extraction.

Ligne 2 : La liste des pays appartenant à votre zone représentée par une série de chaînes de caractères en minuscules non accentuées. Les chaînes sont séparées par des ; (point-virgule).

Ligne 3 à N+2 : le nom, prénom, société, téléphone et pays du client séparés par des ; (point-virgule). Les informations sont composées par : des lettres minuscules non accentuées, des chiffres et les caractères - et +. 



Sortie



3 nombres entiers X Y Z séparés par des espaces.

X est un entier représentant le nombre de doublons

Y est un entier représentant le nombre d'enregistrements incluant un format de téléphone erroné 

Z est un entier représentant le nombre d'enregistrements situés hors de votre zone.



Si une ligne apparaît k fois alors elle compte pour k-1 doublons.



Un enregistrement avec à la fois un téléphone erroné et un pays en dehors de votre zone doit être comptabilisé pour déterminer Y et Z. Par contre, si un enregistrement existe en doublon alors la ou les occurrences suivantes de l'enregistrement doivent être ignorées et ne doivent pas être prises en compte pour déterminer Y et Z.
