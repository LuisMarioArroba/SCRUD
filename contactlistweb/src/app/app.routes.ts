import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./contact-list/contact-list.component')
    },
    {
        path: 'products',
        loadComponent: () => import('./product-list/product-list.component')
    },
    {
        path: 'clients',
        loadComponent: () => import('./client-list/client-list.component')
    },
    {
        path: 'new',
        loadComponent: () => import('./contact-form/contact-form.component')
    },
    {
        path: 'products/new',
        loadComponent: () => import('./product-form/product-form.component')
    },
    {
        path: 'clients/new',
        loadComponent: () => import('./client-form/client-form.component')
    },
    {
        path: 'products/:id/edit',
        loadComponent: () => import('./product-form/product-form.component')
    }
    ,
    {
        path: 'products/venta',
        loadComponent: () => import('./product-form/product-form.component')
    },
    {
        path: 'clients/:id/edit',
        loadComponent: () => import('./client-form/client-form.component')
    }
];
