import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerCargosComponent } from './ver-cargos.component';

describe('VerCargosComponent', () => {
  let component: VerCargosComponent;
  let fixture: ComponentFixture<VerCargosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerCargosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerCargosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
