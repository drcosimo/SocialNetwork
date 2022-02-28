import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PubblicaPostComponent } from './pubblica-post.component';

describe('PubblicaPostComponent', () => {
  let component: PubblicaPostComponent;
  let fixture: ComponentFixture<PubblicaPostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PubblicaPostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PubblicaPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
