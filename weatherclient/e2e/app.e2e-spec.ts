import { WeatherclientPage } from './app.po';

describe('weatherclient App', function() {
  let page: WeatherclientPage;

  beforeEach(() => {
    page = new WeatherclientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
