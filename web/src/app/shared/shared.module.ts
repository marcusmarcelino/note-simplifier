import { CommonModule } from '@angular/common';
import { CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA } from '@angular/core';

import { AppMaterialModule } from './app-material/app-material.module';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { LoadSpinnerCircleComponent } from './components/load-spinner-circle/load-spinner-circle.component';
import { SnackbarComponent } from './components/snackbar/snackbar.component';
import { ToolbarComponent } from './components/toolbar/toolbar.component';
import { CustomButtonComponent } from './components/custom-button/custom-button.component';
import { ModalStandbyComponent } from './components/modal-standby/modal-standby.component';

@NgModule({
  declarations: [
    ToolbarComponent,
    LoadSpinnerCircleComponent,
    SnackbarComponent,
    ErrorDialogComponent,
    CustomButtonComponent,
    ModalStandbyComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
  ],
  exports: [
    ToolbarComponent,
    LoadSpinnerCircleComponent,
    SnackbarComponent,
    ErrorDialogComponent,
    CustomButtonComponent
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA ]
})
export class SharedModule { }
