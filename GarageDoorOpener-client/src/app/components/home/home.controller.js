class HomeController {
  /*@ngInject*/
	constructor(Rest, $timeout){
		this.name = 'home';
    this.keycode = "";
    this.Rest = Rest;
    this.timeout = $timeout;
    this.timeoutPromise = null;
	}

  update(number) {
    this.keycode = this.keycode + number;
  }

  clear() {
    this.timeout.cancel(this.timeoutPromise);
    this.keycode = "";
  }

  submit() {
    if (this.keycode === "") {
      this.keycode = "ERROR";
    } else {
      let requestingKeyCode = this.keycode;
      this.keycode = "WAIT";
      this.Rest.one("api/keycode", requestingKeyCode).post().then(()=> {
        this.keycode = "OPENING";
      }).catch(() => {
        this.keycode = "ERROR";
      });
      this.timeoutPromise = this.timeout(() => {
        this.keycode = "";
      }, 5000);
    }
  }
}

export default HomeController;