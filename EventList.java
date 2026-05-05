public class EventList implements IEventList
{
    private LinkedList<IEvent> events;
    private int size;

    
    public EventList()//constructor
    {
       size=0;
       events = new LinkedList<IEvent>();
    }
    public boolean addEvent(IEvent event)//O(n) 
    {
       if (events.empty())//if the list is empty we can insert the event directly 
        {
          events.insert(event);
          size++;
          return true;
        }

       String eventTitle = event.getTitle();
       events.findFirst();
       for(int i=0;i<size;i++)
       {
          if (eventTitle.compareTo(events.retrieve().getTitle()) <= 0) //we need to insert before the current node
           {
             IEvent tmp = events.retrieve();
             events.update(event); 
             events.insert(tmp);
             size++;
             return true;
           }
               if(!events.last())
                events.findNext();
               
       }
       events.insert(event);
       size++;
       return true; //it always will return true since we use linked list (dynamic data structure ) si there is no limit for insertion except if we have memory limitation 
    }

    public boolean removeEventById(int eventId)//O(n)
    {
        if(events.empty())
          return false; //we can not delete from empty list
        events.findFirst();
        for (int i=0;i<size;i++)//will reach each event in the list until we eathir find the event that we want to delet or reach the end of the list
        {
            if(events.retrieve().getEventId() == eventId)
            {
                events.remove();
                size--;
                return true;
            }
            else
            if(!events.last())//avoid the null pointer exception
               events.findNext();
        }
        
        return false ;//since we get out of the for loop and reach this line this mean that we did not find the event that we want to delete and in this case we need to return false
    }

    public LinkedList<IEvent> getAllAlphabetically()//O(n)
    {
        LinkedList<IEvent> allEvents = new LinkedList<IEvent>();//to store the  copy nodes from the events list
        if(!events.empty())
        {
            events.findFirst();
            for(int i=0;i<size;i++)
              {
                 allEvents.insert(events.retrieve());
                 if(!events.last())//to avoid the null potinter exception 
                  events.findNext();
              }
           return allEvents; 
        }
        else 
          return allEvents;//will return the empty list
    }

    public LinkedList<IEvent> findByTitle(String title)//O(n)
    {
        LinkedList<IEvent> findedEventTitel = new LinkedList<IEvent>();
        if(events.empty())
           return findedEventTitel;//we can not search in empty list and will retuen the empty list
    
        
        events.findFirst();
        for (int i=0;i<size;i++)
        {
            if (events.retrieve().getTitle().equals(title))
            {
                findedEventTitel.insert(events.retrieve());
            }
            if(!events.last ())
              events.findNext ();
        }
        return findedEventTitel;
    }

    public LinkedList<IEvent> findByStudentName(String studentFullName)
    {    

        LinkedList<IEvent> findedEventName = new LinkedList<IEvent>();
        String studentName;
        if(events.empty())
           return findedEventName;//we can not search in empty list. so we will retuen the empty list
        
        events.findFirst();
        for (int i=0;i<size;i++)
        {   // since we don not have a method in IEvent to get the name of students we can use its children classes to easily acsses the student name
            if(events.retrieve() instanceof IMeeting)
            {
                studentName = ((IMeeting)(events.retrieve())).getStudent().getName();
                if(studentName.equalsIgnoreCase(studentFullName))
                  findedEventName.insert(events.retrieve()); 
            }
             if(events.retrieve() instanceof IWorkshop)
             {
                LinkedList<IStudent> workShopL = ((IWorkshop)(events.retrieve())).getParticipants();
                
                if(!workShopL.empty())
                {
                    workShopL.findFirst();
                    boolean found = false;//to help us to get out of the while loop ones we find the student name
                    while(!workShopL.last()&& !found)
                    {
                        if(workShopL.retrieve().getName().equalsIgnoreCase(studentFullName))
                        {          
                            findedEventName.insert(events.retrieve()); 
                            found = true;
                            break;//logicaly the student can not be in the same workshop mork then one time
                        } 
                        workShopL.findNext();
                        
                    }
                    if(workShopL.last()&& !found)//since the [while(!workShop.last())] will not check the last node and this condetion will be true only if we did not find the student name in the nodes before the last one
                    {
                        if(workShopL.retrieve().getName().equalsIgnoreCase(studentFullName))
                          findedEventName.insert(events.retrieve()); 
                    }       
                }
                
             }

             
            if(!events.last ())//to aviod the null pointer exption
              events.findNext ();
            
        
        }
     return findedEventName;
    }
    
    public int size()
    {
        return size;
    }



}