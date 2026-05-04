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
      String eventTitle = event.getTitle();
      if (events.empty())//if the list is empty we can insert the event directly 
        {
         events.insert(event);
         size++;
         return true; //it always will return true since we use linked list (dynamic data structure ) si there is no limit for insertion 
    }

      events.findFirst();

        while(!events.last()  && eventTitle.compareTo(events.retrieve().getTitle())>0  )//use while loop to help us to find the correct position and maintains alphabetical ordiring and use the condition [!events.last()] to avoid the null pointer exception 
        events.findNext();

      events.insert(event);
      size++;
      return true; //it always will return true since we use linked list (dynamic data structure ) si there is no limit for insertion 
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

    public LinkedList<IEvent> getAllAlphabetically()
    {
        if(!events.empty())
           return events;
        else 
        return null;
    }

    public LinkedList<IEvent> findByTitle(String title)//O(n)
    {
        LinkedList<IEvent> findedEventTitel = new LinkedList<IEvent>();
        if(events.empty())
           return findedEventTitel;//we can not search in empty list and will retuen the empty list
    
        
        events.findFirst();
        for (int i=0;i<size;i++)
        {
            if(events.retrieve().getTitle().equals(title))
            {
                findedEventTitel.insert(events.retrieve());
            }
            if(!events.last ())
              events.findNext ();
        }
        return findedEventTitel;
    }

    public LinkedList<IEvent> findByStudentName(String studentFullName)//O(n^2) since we use two loops one to go over the  events and the other one to go over the participants in the workShop
    {
        

        LinkedList<IEvent> findedEventName = new LinkedList<IEvent>();
        String studentName;
        if(events.empty())
           return findedEventName;//we can not search in empty list. we will retuen the empty list
        
        events.findFirst();
        for (int i=0;i<size;i++)
        {   // since we don not have a method in IEvent to get the name of students we can use its children classes to easily acsses the student name
            if(events.retrieve() instanceof IMeeting)
            {
                studentName = ((IMeeting)(events.retrieve())).getStudent().getName();
                if(studentName.equals(studentFullName))
                  findedEventName.insert(events.retrieve()); 
            }
            if(events.retrieve() instanceof IWorkshop)
            {
                LinkedList<IStudent> workShop =  new LinkedList<IStudent>();
                workShop = ((IWorkshop)(events.retrieve())).getParticipants();
                if(workShop.empty())
                     continue;
                  
                workShop.findFirst();
                while(!workShop.last())
                {
                   if(workShop.retrieve().getName().equals(studentFullName))
                     findedEventName.insert(events.retrieve()); 
                   workShop.findNext();
                }
                if(workShop.last())//since the [while(!workShop.last())] will not check the last node 
                 {
                     if(workShop.retrieve().getName().equals(studentFullName))
                     findedEventName.insert(events.retrieve()); 
                 }
            }

            {  
            if(!events.last ())//to aviod the null pointer exption
              events.findNext ();
            }
        
        }
     return findedEventName;
    }
    
    public int size()
    {
        return size;
    }



}