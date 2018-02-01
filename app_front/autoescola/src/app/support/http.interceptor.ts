import { Injectable } from "@angular/core";
import { ConnectionBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers} from "@angular/http";
import { Observable } from "rxjs/Rx";
import { Location } from '@angular/common';

import { environment } from "../../environments/environment";

import { LocalStorageService, SessionStorageService } from 'ngx-webstorage';

@Injectable()

export class InterceptedHttp extends Http {

    private storage = new LocalStorageService();

    constructor(
        backend: ConnectionBackend,
        defaultOptions: RequestOptions,
        storage: LocalStorageService,
        private location: Location) {
        super(backend, defaultOptions);
    }

    request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
        return super.request(url, options);
    }

    get(url: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.get(url, this.getRequestOptionArgs(options)).catch(this.catchErrors());
    }

    post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.post(url, body, this.getRequestOptionArgs(options)).catch(this.catchErrors());;
    }

    put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.put(url, body, this.getRequestOptionArgs(options)).catch(this.catchErrors());;
    }

    delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
        url = this.updateUrl(url);
        return super.delete(url, this.getRequestOptionArgs(options)).catch(this.catchErrors());;
    }

    private updateUrl(req: string) {
        return  environment.origin + req;
    }

    private getRequestOptionArgs(options?: RequestOptionsArgs) : RequestOptionsArgs {
        if (options == null) {
            options = new RequestOptions();
        }
        if (options.headers == null) {
            options.headers = new Headers();
        }
        options.headers.append('Content-Type', 'application/json');
        options.headers.append('Authorization', 'Bearer ' + this.storage.retrieve('token'));

        return options;
    }

    private catchErrors() {
        return (res: Response) => {
            if (res.status === 401 || res.status === 403) {
                this.get('v1/oauth/refresh').subscribe(
                    (res: Response) => {
                        let data = res.json();
                        this.storage.store('token', data.token);
                        window.location.reload();
                    }
                );
            }
            return Observable.throw(res);
        };
    }
}
