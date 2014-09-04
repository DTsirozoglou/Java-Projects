Principals of Computer System Design (PCSD) :

Developed  an  integration  broker  abstraction  in  a  simplified  order  processing 
scenario. The task was implemented in Java,  compatible with JDK 6. Jetty was used 
as  an  RPC  mechanism.  For  the  completion  of  the  project  all  the  following  aspects 
were encountered:

Design of transactional and distributed system.


Enforce modularity through a client-service abstraction.

Concurrency control and recovery, as well as for distribution and replication.

Implementation of a system that includes mechanisms for modularity, atomicity,and fault tolerance.

Structure and conduct experiments to evaluate a system's performance.

Explain techniques for large-scale data processing.

Apply principles of large-scale data processing to concrete problems.
 
The Scenario :

A global manufacturer, such as an airplane  builder,  acquires items  from  multiple  suppliers  around  the  world,  and  employs these  items  in their  own  manufacturing  process. For  the  global  manufacturer,  it  is  important  to control  orders  for multiple items across suppliers; for the local suppliers; it is important to correctly record and query the orders received from the global manufacturer. 

Since  different  suppliers  employ multiple order  processing  systems, the  global  manufacturer needs  an 
integration middleware, often referred to as an integration broker, to coordinate order workflows across 
the  order  processing  systems  of  the  suppliers. The  integration  broker  is typically implemented over  a 
durable communication  abstraction. Workflows  are  submitted  to  this  broker  component  and  executed 
asynchronously.  While  workflows  are  not  atomic  as  a  whole,  order  registration  steps  with individual 
suppliers are atomic.

We model the broker abstraction in this assignment by an OrderManager interface, and expose order 
processing systems of item suppliers through a common ItemSupplier interface.  For simplicity, we 
make the integration broker unidirectional, i.e., an order manager accepts order workflow requests from 
global clients, and executes them against item suppliers, but no requests flow from item suppliers back to
order managers or to other item suppliers. Given this restriction, we expose ItemSupplier instances 
as  services,  accessible  to OrderManager  instances  or  local  clients  via  RPC;  OrderManager
instances themselves are also accessible via RPC to global clients. 
