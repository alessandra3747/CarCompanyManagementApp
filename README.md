# CarCompanyManagementApp
## An application simulating a simplified process of ordering/paying in a car rental company that rents cars by kilometers.
* [POLSKI](#cel-programu)
  * [CEL PROGRAMU](#cel-programu)
  * [OPIS ZADANIA](#opis-zadania)
* [ENGLISH](#purpose-of-the-program)
  * [PURPOSE OF THE PROGRAM](#purpose-of-the-program)
  * [TASK DESCRIPTION](#task-description)

### Cel programu:  
Celem programu jest symulacja uproszczonego procesu wynajmu samochodów według przejechanych kilometrów w wypożyczalni samochodów. Umożliwia on klientom wybór, zamówienie i opłacenie różnych typów pojazdów na podstawie dostosowywalnej listy cenowej. <br /><br /> 
Program obsługuje kluczowe funkcjonalności takie jak rejestracja klientów, zarządzanie listą życzeń i koszykiem klienta, obsługa płatności, w tym wsparcie dla płatności częściowych, naliczanie opłat oraz zarządzanie zwrotami niewykorzystanych kilometrów. <br /> 
Program jest zaprojektowany w sposób elastyczny, wspierający różne typy samochodów i scenariusze cenowe. Dodatkowo, stosuje zasady programowania obiektowego, takie jak polimorfizm i abstrakcja, aby zapewnić modularność i skalowalność.

### Opis zadania

Napisać aplikację symulującą uproszczony proces zamawiania/płacenia w pewnej firmie wynajmującej 
samochody na kilometry.

Firma oferuje różne typy samochodów, każdy samochód ma swoją markę/model oraz typ (np. osobowy, dostawczy, 
zabytkowy, ...). <br />
Samochody mogą być zamawiane na daną odległość (tzn. nie dłuższą niż) mierzoną w kilometrach. 
Ceny za wynajem znajdują się w cenniku. *Cennik można modyfikować w dowolnym momencie*.

Klienci po rejestracji otrzymują identyfikatory oraz deklarują kwotę pieniędzy, którą chcą wydać na wynajem. <br />
Klienci mogą (ale nie muszą) posiadać abonament w firmie. <br />
Klienci wybierają samochody i umieszczają je na swojej tzw. liście życzeń. Następnie, być może po jakimś czasie, w celu finalizacji zamówienia przepakowują 
samochody do koszyka (po uprzednim wyczyszczeniu) i zapłacą za jego zawartość. <br />
*Każdy klient może mieć tylko 1 listę życzeń i 1 koszyk*. <br />
Klienci mogą płacić kartą lub przelewem bankowym. Po zapłaceniu, klient ma 
możliwość dokonania zwrotu (tylko z ostatniej transakcji) części lub całej ilości niewykorzystanych kilometrów.

##### Ważne uwagi:
- Być może są samochody niemające ceny w cenniku, wtedy przy przepakowaniu z listy życzeń do koszyka nie są one
  usuwane z listy życzeń i nie trafią do koszyka.
- Niektóre samochody posiadają różne ceny w zależności od ilości zamówionych kilometrów.
- Jeśli klient nie ma wystarczającej ilości pieniędzy na całą zawartość koszyka to, zależy od opcji, może 
  zapłacić za część koszyka o ile zapłacona kwota (za dany samochód) jest wielokrotnością ceny tego samochodu 
  za 1 km (zgodnie z cennikiem), w takim przypadku o wyborze samochodów/kilometrów do zapłacenia zadecyduje 
  aplikacja.
- Przy płaceniu kartą płatniczą klient zapłaci dodatkowo 1% prowizji.
- Przy zwrocie samochodów/kilometrów z ostatniej transakcji, aplikacja powinna zwrócić klientowi właściwą 
  ilość środków uwzględniając faktycznie niezwrócone kilometry (zgodnie z cennikiem). Nie ma zwrotu prowizji.
- Zdefiniowanie odpowiedniej metody toString() w niektórych klasach jest obowiązkowe.
<br />

### Purpose of the program:
The purpose of this program is to simulate a simplified process for renting cars by the kilometer in a car rental company. It allows customers to select, order, and pay for different types of vehicles based on a customizable price list. <br /><br />
The program handles key functionalities such as customer registrations, wish lists, carts, and payments, including support for partial payments, applying fees, and managing returns of unused kilometers.<br />
The program is designed to be flexible, supporting various car types and pricing scenarios, and employs object-oriented principles such as polymorphism and abstraction to ensure modularity and scalability.

### Task Description

Write an application simulating a simplified process of ordering/paying in a certain car rental company that 
rents cars by the kilometers.

The company offers various types of cars, each with its own make/model and type  (e.g., passenger, delivery, vintage, etc.). <br /> 
Cars can be ordered for a specified distance (not exceeding a given limit) measured in kilometers.<br />
Rental prices are listed in a price list. *The price list can be modified at any time.*

Customers, upon registration, receive identifiers and declare the amount of money they want to spend on rentals. <br />
Customers may (but do not have to) have a subscription with the company. <br />
Customers choose cars and place them on their so-called wish list. Then, possibly after some time, to finalize the order, they transfer 
the cars to the cart (after clearing it first) and pay for its contents. <br />
*Each customer can have only one wish list and one cart.* <br />
Customers can pay by card or bank transfer. After payment, the customer has the option to return (only from the last transaction) part or all of the unused kilometers.

##### Important notes:
- There may be cars that do not have a price in the price list; in such cases, when transferring from the 
  wish list to the cart, they are not removed from the wish list and do not enter the cart.
- Some cars have different prices depending on the number of kilometers ordered.
- If the customer does not have enough money for the entire cart's contents, they may be able to pay for part
  of the cart, provided that the paid amount (for a given car) is a multiple of the car's price per kilometer 
  (according to the price list). In such a case, the application will decide which cars/kilometers to pay for.
- When paying by credit card, the customer will pay an additional 1% fee.
- When returning cars/kilometers from the last transaction, the application should refund the customer the 
  appropriate amount of money, taking into account the actual kilometers not returned 
  (according to the price list). The fee is non-refundable.
- Defining an appropriate `toString()` method in certain classes is mandatory.
