# Vehicle Rental System
 All the prices are kept in one class for easy changes

 To get the end result first we create the vehicles to add to the system, using the classes Car, Motorcycle and CargoVan, which extend the  
 BaseVehicle abstract class (room for more types of vehicles)

 Then we create an Invoice object and pass a vehicle along with other necessary data, the Invoice class (based on the arguments) decides 
 which type of invoice to create. The new invoice assembles the final invoice and afterwards the Invoice class takes the string representing 
 it. Then we print the result.

 For future improvement, it would be a good idea to implement the factory method for the invoices.
