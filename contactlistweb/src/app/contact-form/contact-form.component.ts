import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContactService } from '../services/contact.service';
import { Contact } from '../model/contact.interface';

@Component({
  selector: 'app-contact-form',
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export default class ContactFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private contactService = inject(ContactService);
  
  form?: FormGroup;
  contact?: Contact;

  ngOnInit(): void {
      const id = this.route.snapshot.paramMap.get('id');
      var methodType="POST";
      if (id){
        methodType="PUT";
        this.contactService.get(parseInt(id)).subscribe(contact => {
          this.contact = contact;
          this.form=this.fb.group({
            name:[contact.name, [Validators.required]],
            email: [contact.email, [Validators.required, Validators.email]],
            fecha: [contact.registerAt, [Validators.required]]
          })
          console.log('c',contact);
        })
      }else{
        this.form=this.fb.group({
          name:['', [Validators.required]],
          email: ['', [Validators.required, Validators.email]],
          fecha: ['', [Validators.required]]
        })
      }
  }

  save() {
    const contactform = this.form!.value;
    if(this.contact){
        this.contactService.update(this.contact.id,contactform).subscribe(() => {
          this.router.navigate(['/']);
        });
    }else{
      this.contactService.create(contactform).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
    if (this.form!.invalid) {
      this.form!.markAllAsTouched(); // Marca todos los campos como tocados para que muestren errores
      return;
    }
    console.log(this.form!.value);
  }

}
