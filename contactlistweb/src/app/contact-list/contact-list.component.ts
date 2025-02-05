import { Component, inject, OnInit } from '@angular/core';
import { ContactService } from '../services/contact.service';
import { DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-list',
  imports: [DatePipe, RouterModule],
  templateUrl: './contact-list.component.html',
  styleUrl: './contact-list.component.css'
})
export default class ContactListComponent implements OnInit {
  private contactService=inject(ContactService)

  contacts: Contact[] =[];

  loadAll(){
    this.contactService.list().subscribe(contacts => {
        this.contacts=contacts
      }
    )
  }

  ngOnInit(): void {
    this.loadAll();
  }


  deleteContact(contact: Contact){
    this.contactService.delete(contact.id).subscribe(()=>{
      this.loadAll();
      console.log("Elemento eliminado")
    })
  }
}
